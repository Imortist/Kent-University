---------------------
--      LAMBDAS
---------------------

applyTwice :: (a->a) -> a -> a
applyTwice f x = f $ f x

---------------------
-- Higher List Func
---------------------

myMap :: (a -> b) -> [a] -> [b]
myMap f [] = []
myMap f (x:xs) = f x : myMap f xs

myFilter :: (a -> Bool) -> [a] -> [a]
myFilter p []            = []
myFilter p (x:xs)
            | p x        =  x : myFilter p xs
            | otherwise  = myFilter p xs

myFoldr :: (a -> b -> b) -> b -> [a] -> b
myFoldr cons nil []     = nil
myFoldr cons nil (x:xs) = cons x (myFoldr cons nil xs)

-------------------
--    THEOREMS
-------------------
