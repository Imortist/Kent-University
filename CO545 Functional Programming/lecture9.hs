-- p1 Type Classes

import Prelude hiding (Ordering)

-- matches one is defined as for all types a
-- which have an implementation of the Eq (equality) class.
matchesOne :: Eq a => (a, a) -> a -> Bool
matchesOne  (x,y) z = (x == z) || (y == z)

data ColoursX = R | G | B | RGB Int Int Int
  deriving Show

data Item = Stick | Spoon
  deriving (Eq, Ord, Show)

data BTree a = Leaf | Node a (BTree a) (BTree a)

example = Node 1 (Node 2 Leaf Leaf) (Node 3 Leaf Leaf)

instance Eq ColoursX where
  R             == R = True
  G             == G = True
  B             == B = True
  (RGB r g b)   == (RGB r' g' b') = r == r' && g == g' && b == b'
  (RGB 255 0 0) == R = True
  (RGB 0 255 0) == G = True
  (RGB 0 0 255) == B = True
  R             == (RGB 255 0 0)  = True
  G             == (RGB 0 255 0)  = True
  B             == (RGB 0 0 255) = True
  _             == _ = False


instance Show a => Show (BTree a) where
   show Leaf = "*"
   show (Node n l r) = "(" ++ show l ++
                       "-{" ++ show n ++
                       "}-" ++ show r ++ ")"

--p2 Defining classes

class Eq t => Ordering t where
  lessThan   :: t -> t -> Bool

  lessThanEq :: t -> t -> Bool
  lessThanEq x y = x `lessThan` y || x == y

  myMax      :: t -> t -> t
  myMax x y
        | x `lessThan` y = y
        | otherwise      = x


instance Ordering Item where
  lessThan Stick Spoon = True
  lessThan _ _ = False

  --p3 expressions of a language

data Expr = Plus Expr Expr
          | Mult Expr Expr
          | Neg Expr
          | Const Integer
          | Var String


prog1 = Plus (Const 1) (Const 2)
prog2 = Mult (Const 2) prog1
prog3 = Plus (Var "x") (Plus (Const 1) (Var "y"))