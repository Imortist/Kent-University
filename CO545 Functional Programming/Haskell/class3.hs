-------------------------------
--          Part 1
-------------------------------

--1.1
-- incorrect list, lists hold one type of data
--
import Data.Char

mess = (42,('a',615),(2600,(29, True)))

convert :: [Char] -> Int
convert [] = 0
convert (x:xs) = ord x + convert xs


get1 :: (a,b,c) -> a
get1 (a,_,_) = a

get2 :: (a,b,(c,(d,e))) -> d
get2 (_,_,(_,(a,_))) = a

onlyPositives :: [Integer] -> [Integer]
onlyPositives [] = []
onlyPositives (x:xs)
              | x >= 0 = [x] ++ onlyPositives xs
              | x < 0 = onlyPositives xs

countPositives :: [Integer] -> Int
countPositives [] = 0
countPositives x = length (onlyPositives x)

-- because we want to count positive numbers not anything else,
-- so we have to bound variables

checkLength :: [Integer] -> Int -> Bool
checkLength a b = length a == countPositives a

copyTuple :: a -> (a,a)
copyTuple a = (a,a)

charList = ['a','b','c','d','e']

dropEvens :: [a] ->[a]
dropEvens [] = []
dropEvens [x] = []
dropEvens (x:y:xs) = y : (dropEvens xs)

ix :: [a] -> Integer -> a
ix [] _ = undefined
ix (x:xs) 0 = x
ix (x:xs) i = ix xs (i-1)