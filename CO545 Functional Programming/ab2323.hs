--This is a test :)

--1. p -> Bool

--2.a
fromListToPair :: [a] -> (a,a)
fromListToPair (x:y:xs) = (x,y)

--2.b
q2example = fromListToPair [True,False,False,True]

--3.a
squash :: [(Integer, Integer)] -> [Integer]
squash []      = []
squash [(a,b)] = (a+b) : []
squash (x:xs)  =  let (a,b) = x in (a+b) : squash xs

--3.b
q3example = squash[(10,11),(100,200)]

--4.a
isLessThan :: (a -> Integer) -> [a] -> Integer -> [Bool]
isLessThan f [] _                 = []
isLessThan f (x:xs) a | f x < a   = True : isLessThan f xs a
                      | otherwise = False : isLessThan f xs a

--4.b
q4example = isLessThan (*100) [1,2,3,4] 200