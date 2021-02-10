maxList :: [Integer] -> Integer
maxList (x :[]) = x
maxList (x :xs) =
  let y = maxList xs
  in if x >= y then x else y

reverseRec :: [a] -> [a]
reverseRec [] = []
reverseRec (x :xs) = reverseRec xs ++ [x]

betterReverse :: [a] -> [a]
betterReverse xs = reverseAux [] xs
  where
    reverseAux :: [a] -> [a] -> [a]
    reverseAux rxs [] = rxs
    reverseAux rxs (x:xs) = reverseAux (x:rxs) xs
