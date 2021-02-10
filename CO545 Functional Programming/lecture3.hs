identify :: x -> x
identify x = x

const :: a -> b -> a
const x y = x

boolId :: Bool -> Bool
boolId = id

consta :: Bool -> b -> Bool
consta = consta

------------------------
--        LISTS
------------------------

l = [1,2,3]

myHead :: [a] -> a
myHead (x : xs) = x

myTail :: [a] -> [a]
myTail (x :xs) = xs

myLength []     = 0
myLength (x:xs) = 1 + length xs

mySum:: [Integer] -> Integer
mySum[]        = 0
mySum(hd : tl) = hd + mySum tl

contains0 :: [Integer] -> Bool
contains0 []        = False
contains0 (0 : xs)  = True
contains0 ( _ : xs) = contains0 xs

pairs :: [Integer] -> Integer
pairs[]                     = 0
pairs[x]                    = 0
pairs (a:b:xs) | (b-a == 1) = 1 + pairs xs
               | otherwise  = pairs xs

doubleList :: [Integer] -> [Integer]
doubleList[]     = []
doubleList[x]    = [2*x]
doubleList(x:xs) = x*2: doubleList xs

justEvens :: [Integer] -> [Integer]
justEvens[]                     = []
justEvens(x:xs) | x `mod` 2 > 0 = justEvens xs
                | otherwise     = x : justEvens xs

takeNumbers :: Integer -> [a] -> [a]
takeNumbers _ []     = []
takeNumbers 0 _      = []
takeNumbers a (x:xs) = x : takeNumbers (a-1) xs

myDrop :: Integer -> [a] -> [a]
myDrop _ []     = []
myDrop 0 (x:xs) = x:xs
myDrop n (x:xs) = myDrop(n-1) xs

-----------------------------------
--            TUPLES
------------------------------------

myFirst :: (a,b) -> a
myFirst (x,y) = x

mySecond :: (a,b) -> b
mySecond (x,y) = y

myFirstThree :: (a,b,c) -> a
myFirstThree (x,y,z) = x

-- countEvensAnd Odds takes list and returns tuple with even count and odd count
countEvensAndOdds :: [Integer] -> (Integer, Integer)
countEvensAndOdds []               = (0,0)
countEvensAndOdds (x:xs)
  | mod x 2 == 0 =  let (even, odd) = countEvensAndOdds xs in (even + 1, odd)
  | otherwise    =  let (even, odd) = countEvensAndOdds xs in (even, odd + 1)