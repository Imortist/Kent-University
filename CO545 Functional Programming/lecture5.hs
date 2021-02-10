--------------------------------
--      Pattern Matching
--------------------------------

myUnzip :: [(a,b)] -> ([a], [b])
myUnzip [] = ([],[])
myUnzip ((l,r):xs) =
  let (ls,rs) = myUnzip xs in (l:ls, r:rs)

myZip :: [a] -> [b] -> [(a,b)]
myZip [][] = []
myZip [x][y] = [(x,y)]
myZip (x:xs) (y:ys) = myZip [x][y] ++ myZip xs ys

--------------------------------
--      Functions are Data
--------------------------------

plus :: Integer -> Integer -> Integer
plus x y = x+y

plusP :: Integer -> Integer -> Integer
plusP = plus

myUncurry :: (a -> b -> c) -> ((a,b) -> c)
myUncurry f (x,y) = f x y

myCurry :: ((a,b) -> c) -> a -> b -> c
myCurry f x y = f(x,y)
