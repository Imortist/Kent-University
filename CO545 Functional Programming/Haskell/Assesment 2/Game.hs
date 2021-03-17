{-
For task 17 for bonus points, implemented help menu. type help ingame to see instructions.
-}

import Base
import System.IO


fromJust (Just a) = a
fromJust (Nothing) = Nothing

-- Chunk 1 Task 1
opposite :: Direction -> Direction
opposite South = North
opposite North = South
opposite East = West
opposite West = East

--Chunk 1 Task 2
noActions :: Item -> GameState -> Next GameState
noActions i gs = Same ("No actions with " ++ show i)

--Chunk 1 Task 3
winRoom = Room "win room"
               "you have found the final room"
               True
               (Just Key)
               []
               []
               []
               noActions

--Chunk 1 Task 4
startRoom = Room "start room"
                 "this is where you start"
                 False
                 Nothing
                 [(Spoon,"")]
                 []
                 [(East, winRoom), (West, monsterRoom)]
                 noActions

--Chunk1 Task 5
monsterRoom = Room "monster room"
                   "we have a monster here"
                   False
                   Nothing
                   []
                   [WoodTroll 10 Key]
                   [(East, startRoom)]
                   action

damageMonster :: Monster -> Int -> Monster
damageMonster m d = m {health = (health m) -5}

updateRoom :: Room -> [Monster] -> Room
updateRoom r m = r{monsters = m}

action :: Item -> GameState -> Next GameState
action i gs | i == Spoon = let mos = monsters (room gs) in
  if null mos then Same "There are no monsters to fight"
  else let (m:ms) = mos in
    if health m > 5 then Progress "You hit that dude!" gs{room = (updateRoom (room gs) [
    (damageMonster m 5)])}
    else let newPlayer = (player gs){inventory = (inventory (player gs)) ++ [Key]} in
      Progress "You killed that dude!" gs{player = newPlayer, room = (updateRoom (room gs) [])}

--Chunk 1 Task 6
game0 = GS{player = Player "John Silver" [], room = startRoom}

--Chunk 2 Task 7
instance Parsable Item where
  parse "spoon"  = Just Spoon
  parse "key"    = Just Key
  parse _        = Nothing

--Chunk 2 Task 8
instance Parsable Direction where
  parse "north" = Just North
  parse "south" = Just South
  parse "east"  = Just East
  parse "west"  = Just West
  parse _       = Nothing

--Chunk 2 Task 9
instance Parsable Command where
  parse ('g':'o':' ':direction) =
    case parse direction of
      Just North -> Just (Move North)
      Just South -> Just (Move South)
      Just East  -> Just (Move East)
      Just West  -> Just (Move West)
      Nothing    -> Nothing

  parse('g':'r':'a':'b':' ': item) =
    case parse item of
      Just Spoon -> Just (PickUp Spoon)
      Just Key   -> Just (PickUp Key)
      Nothing    -> Nothing

  parse ('u':'s':'e':' ' : item) =
    case parse item of
      Just Key   -> Just (Use Key)
      Just Spoon -> Just (Use Spoon)
      Nothing    -> Nothing

  parse "end" = Just End

  --Bonus
  parse "help" = Just Help

  parse _ = Nothing

--Chunk 2 Task 10
tellResponse :: String -> IO ()
tellResponse s = putStrLn ("< " ++ s ++ " .")

-- Chink 2 Task 11
readCommand :: IO (Maybe Command)
readCommand = do
  putStr ">"
  command <- getLine
  return (parse command)

--Chunk 3 Task 12
deleteFrom :: Eq a => a -> [(a,b)] -> [(a,b)]
deleteFrom _ [] = []
deleteFrom a [(b,c)] | a==b = []
                     | a /=b = [(b,c)]
deleteFrom a (x:xs) = (deleteFrom a [x]) ++ (deleteFrom a xs)

--Chunk 3 Task 13
leaveRoom :: Room -> Direction -> Room -> Room
leaveRoom fromRoom to toRoom =
  let toDoors = deleteFrom (opposite to) (doors toRoom) in
    toRoom{doors = toDoors ++ [((opposite to), fromRoom)]}

--Chunk 3 Task 14
--must check for requirement of the door in tuple
requirementMet :: Room -> GameState -> Bool
requirementMet room gs = case requires room of
  Nothing -> True
  Just i -> if i `elem` (inventory (player gs)) then True else False


step :: Command -> GameState -> Next GameState
step (Move dir) gs =
  case lookup dir (doors (room gs)) of
    Just r -> if requirementMet r gs then Progress "moving on" gs{room = leaveRoom (room gs) dir r}
              else Same "You dont have the item required"
    Nothing -> Same "There are no doors in that direction"

step (PickUp item) gs =
  case lookup item (items (room gs)) of
    Nothing -> Same "There is no items to pick up"
    Just i -> let itemlessRoom = (room gs){items = (deleteFrom item (items (room gs)))}
                  playerWithitem = (player gs){inventory = [item]} in
                    Progress "You've picked up an item" gs{player = playerWithitem, room =
                      itemlessRoom}

step (Use item) gs | item `elem` (inventory (player gs)) = (actions (room gs)) item gs
                   | otherwise = Same "You dont have an item"

--Chunk 3 Task 15
play :: GameState -> IO ()
play gs = tellContext gs  >> playLoop gs

playLoop :: GameState -> IO ()
playLoop gs = if isWinRoom (room gs) then return() else
  do
    command <- readCommand
    case command of
       Just End -> putStrLn "Thank you for playing, goodbye..." >> return ()
       --Bonus
       Just Help -> putStrLn "Available commands:" >>
                    putStrLn ("go EAST/WEST/SOUTH/NORTH -" ++ "allows to move between rooms") >>
                    putStrLn "use spoon/key - allows to use items" >>
                    putStrLn "grab spoon/key - will pick up item" >>
                    putStrLn "end - will terminate the game." >> playLoop gs
       Just (Move dir) -> case step (Move dir) gs of
                   Same s  -> putStrLn s >> playLoop gs
                   Progress s g -> putStrLn s >> tellContext g >> playLoop g
       Just (Use item) -> case step (Use item) gs of
                   Same s -> putStrLn s >> playLoop gs
                   Progress s g -> putStrLn s >> tellContext g >> playLoop g
       Just (PickUp item) -> case step (PickUp item) gs of
                   Same s -> putStrLn s >> playLoop gs
                   Progress s g -> putStrLn s >> tellContext g >> playLoop g
       Nothing -> putStrLn "Command not recognised" >> playLoop gs

--Chunk 3 Task 16
main = do
   play game0