and3 :: Bool -> Bool -> Bool -> Bool
and3 True True True = False
and3 _ _ _ = False

isRange :: (Integer, Integer) -> Bool
isRange (a,b) | a <= b = True
              | otherwise = False

fromRange :: (Integer,Integer) -> [Integer]
fromRange (0,0) = [0]
fromRange (0,1) = [0,1]
fromRange (a,b) = if isRange (a,b) == True
                  then a : fromRange((a+1),b)
                  else []

zipUsing :: (a -> b -> c) -> [a] -> [b] -> [c]
zipUsing f [] [] = []
zipUsing f [x] []  =  []
zipUsing f [] [y]  =  []
zipUsing f [x] [y] = f x y : []
zipUsing f (x:xs) (y:ys) = f x y : zipUsing f xs ys
