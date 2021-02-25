--p1 Binary Search Trees
--type synonims: type String = [Char]

import Prelude hiding (sum)

example = Node 2 (Node 1 Leaf Leaf) (Node 3 Leaf Leaf)

data BTree a = Leaf | Node a (BTree a) (BTree a)
  deriving Show

type BST = BTree Integer

member :: BST -> Integer -> Bool
member Leaf _         = False
member (Node n l r) x =
  if n == x then True
  else
    if x < n then member l x
             else member r x


insert :: BST -> Integer -> BST
insert Leaf x = Node x Leaf Leaf
insert (Node n l r) x =
  if x <= n
    then Node n (insert l x) r
    else Node n l (insert r x)


flatten :: BST -> [Integer]
flatten Leaf = []
flatten (Node n l r) = flatten l ++ [n] ++ flatten r


grow :: [Integer] -> BST
grow xs = growAux xs Leaf

growAux :: [Integer] -> BST -> BST
growAux [] t = t
growAux (x: xs) t = growAux xs (insert t x)

--p2 LaZZiNeSS

nats :: [Integer]
nats = go 0
  where
    go n = n : go (n+1)

mkCycle :: [Integer] -> [Integer]
mkCycle xs = xs ++ mkCycle xs

circle123 = 1 : 2 : 3 : circle123

--p3 Tail recurrsion

sum :: [Integer] -> Integer
sum xs = sumT 0 xs

sumT :: Integer -> [Integer] -> Integer
sumT acc [] = acc
sumT acc (x : xs) = sumT (x + acc) xs

--non tr
maxOfList :: [Integer] -> Integer
maxOfList [x] = x
maxOfList (x : xs) =
  max x (maxOfList xs)

maxOfListTail :: [Integer] -> Integer
maxOfListTail (x : xs) = aux x xs
  where
    aux :: Integer -> [Integer] -> Integer
    aux m [] = m
    aux m (x : xs) = aux (max m x) xs

fibSlow :: Integer -> Integer
fibSlow 0 = 0
fibSlow 1 = 1
fibSlow n = fibSlow (n - 1) + fibSlow (n - 2)

fibTail :: Integer -> Integer
fibTail 0 = 0
fibTail n = fibAux 1 0 (n-1)
  where
    fibAux :: Integer -> Integer -> Integer -> Integer
    fibAux cur prev 0 = cur
    fibAux cur prev n = fibAux (cur + prev) cur (n-1)