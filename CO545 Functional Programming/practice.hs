--find doubles

doubleSmallNumber x = if x > 100 then x else x*2
myList :: [Int]
myList = [1,2,3,4,5]
secondList :: [Int]
secondList = [6,7,8,9,0]

pairs :: [Int] -> Int
pairs [] = 0;
pairs (a:[]) = 0
pairs (a:b:cs) = if (b-a) == 1 then 1 + pairs cs else pairs cs

removeNonUpperCase st = [c | c <- st, c `elem` ['A'..'Z']]

toCamelCase :: String -> String
toCamelCase st =