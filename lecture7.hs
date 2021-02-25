import Data.Char

-- p1 Basic Data Types

data ColoursX = R | G | B | RGB Int Int Int
  deriving Show

toHTML :: ColoursX -> String
toHTML R          = "#f00"
toHTML G          = "#0f0"
toHTML B          = "#00f"
toHTML(RGB x y z) = "rbg(" ++
                    show x ++
                    show y ++
                    show z ++
                    ")"


redToGreen :: Bool -> ColoursX
redToGreen True  = R
redToGreen False = G

data IntOrChar = IsInt Int | IsChar Char
  deriving Show

toChar :: IntOrChar -> Char
toChar (IsInt n)  = chr n
toChar (IsChar c) = c

toInt :: IntOrChar -> Int
toInt (IsInt n)  = n
toInt (IsChar c) = ord c

--p2 Parametric Data Types

--data Maybe a = Just a | Nothing
--  deriving Show

divTotal :: Int -> Int -> Maybe Int
divTotal x 0 = Nothing
divTotal x y = Just (x `div` y)

safeHead :: [a] -> Maybe a
safeHead []      = Nothing
safeHead (x : _) = Just x

scaleHead :: [Integer] -> Integer
scaleHead xs =
    case safeHead xs of
      Nothing -> 0
      Just x -> x * 10

mapMaybe :: (a -> Maybe b) -> [a] -> [b]
mapMaybe f []       = []
mapMaybe f (x : xs) =
  case f x of
    Nothing -> mapMaybe f xs
    Just y -> y : mapMaybe f xs

-- data Either a b = Left a | Right b
ifThenElse :: Bool -> a -> b -> Either a b
ifThenElse True x _  = Left x
ifThenElse False _ y = Right y


--p3 Recursive Data Types

data List a = Nil | Cons a (List a)
  deriving Show

myHead :: List a -> Maybe a
myHead Nil        = Nothing
myHead (Cons x _) = Just x

myMap :: (a->b) -> List a -> List b
myMap f Nil         = Nil
myMap f (Cons x xs) = Cons (f x) (myMap f xs)

secondElement :: List a -> a
secondElement (Cons _ (Cons x _)) = x

fromMyList :: List a-> [a]
fromMyList Nil         = []
fromMyList (Cons x xs) = x: (fromMyList xs)

data Btree a = Leaf | Node a (Btree a) (Btree a)
  deriving Show

example = Node 1 (Node 2 Leaf Leaf) (Node 3 Leaf Leaf)

sumTree :: Btree Integer -> Integer
sumTree Leaf         = 0
sumTree (Node n l r) = n + sumTree l + sumTree r

flatten :: Btree a -> [a]
flatten Leaf         = []
flatten (Node n l r) = n : (flatten l ++ flatten r)

grow :: [a] -> Btree a
grow []       = Leaf
grow (x : xs) = Node x Leaf (grow xs)

-- map :: (a->b) -> [a] -> [b]
mapTree :: (a -> b) -> Btree a -> Btree b
mapTree f Leaf = Leaf
mapTree f (Node x l r) = Node (f x) (mapTree f l) (mapTree f r)

