--class 2 functional programming HASKELL

import Prelude hiding ((&&), (||), abs)
-------------------------------
-- 1 GROUP DISCUSSION
-------------------------------
--1.1
ooz :: (Eq a, Num a) => a -> Bool
ooz 0 = True
ooz 1 = True
ooz _ = False

--a returns true if passed 1 or 0, otherwise returns false. will take only digits
--b any digit floating point number or integer
--c ooz (ooz 2) is a recursive call of function. It wont compile since ooz takes numerical values, not bools

--d
--ooz' _ = False
--ooz' 0 = True
--ooz' 1 = True
-- This function has different behaviour, it will always return FALSE.

--e
ooz'' 1 = True
ooz'' 0 = True
ooz'' _ = False
--This function has the same behaviour as ooz.

--f They flip boolean values
f :: Bool -> Bool
f x = if x then False else True
g:: Bool -> Bool
g True = False
g False = True

--g
angryPanda x = g (ooz x)

--1.2
-- Function is an OR expression
-- returns true if one or both values are true, returns false if both values are false
foo :: Bool -> Bool -> Bool
foo True x = True
foo x    y = y

--1.3
-- returns absolute value of an integer (cannot use abs since its predefined in Haskell
abs :: Integer -> Integer
abs x | x < 0 = -x
abs x         = x

--1.4
--a. power 2 3 => 8 & power 10 2 => 100;
--b. function takes 2 integers as parameters, if second parameter is 0 returns 1
--   otherwise recursive call to power with deminishing m value on each iteration
--c. power 2 (-3) is infinite loop, we could convert negative integers to absolute value
--   or make another function for negative power

power :: Integer -> Integer -> Integer
power n 0 = 1
power n m | m > 0 = n * power n (m-1)
power n m | m < 0 = n * power n ((abs m) - 1)


---------------------------------
--2
---------------------------------

sumInterval :: Integer -> Integer -> Integer
sumInterval a b | a > b = 0
sumInterval a b | a == b = a
sumInterval a b | a < b = a + sumInterval (a+1) b

---------------------------------
--3
---------------------------------
doubling :: Integer -> Integer
doubling a = a*2

quadring :: Integer -> Integer
quadring a = doubling (doubling a)

isEven :: Integer -> Bool
isEven a = if  a == 0 then True else
           if (abs) a == 1 then False else isEven((abs)a-2)


(&&) :: Bool -> Bool -> Bool
(&&) True True = True
(&&) _ _ = False

(||) :: Bool -> Bool -> Bool
(||) False False = False
(||) _ _ = True

(|/) :: Bool -> Bool -> Bool
(|/) a b | a == b = False
(|/) _ _ = True

isRange :: Integer -> Integer -> Integer -> Bool
isRange a b c = if (a <= c) && (c <= b) then True else False