trail :: Integer -> Integer
trail = (\x -> if x then 100 else 200) .
        not .
        (<500) .
        (+ 10) .
        (* 100) .
        (+ 1)

-- 2. fun2 is total function
-- all of it's parameters are in the domain

mapFilter :: (a -> b) -> (b -> Bool) -> [a] -> [b]
mapFilter f c [] = []
mapFilter f c (x:xs) = if c (f x)
                        then f x : mapFilter f c xs
                         else mapFilter f c xs