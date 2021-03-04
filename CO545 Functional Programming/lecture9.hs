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
  deriving Show


prog1 = Plus (Const 1) (Const 2)
prog2 = Mult (Const 2) prog1
prog3 = Plus (Var "x") (Plus (Const 1) (Var "y"))
prog4 = Assign "z" (Mult prog3 prog2)
progMain =
  Seq (Assign "x" (1 + 2))
   $ Seq (Assign "y" (Var "x" +4))
   $ Seq (Assign "z" ((Var "x") * (Var "y")))
   $ Empty

instance Num Expr where
  x + y    = Plus x y
  x - y    = Plus x (Neg y)
  x * y    = Mult x y
  negate x = Neg x
  abs x    = undefined
  signum x = undefined
  fromInteger x = Const x

-- p3 Association List aka Java Map, aka C dictionary

evalExpr :: [(String, Integer)] -> Expr -> Integer
evalExpr env (Plus e1 e2) = evalExpr env e1 + evalExpr env e2
evalExpr env (Mult e1 e2) = evalExpr env e1 * evalExpr env e2
evalExpr env (Neg e)      = - evalExpr env e
evalExpr env (Const n)    = n
evalExpr env (Var n)      =
  case lookup n env of
    Just n -> n
    Nothing -> error $ "Unknown variable" ++ n

env :: [(String, Integer)]
env = [("x", 42), ("y", 100)]

data Stmt = Assign String Expr
  deriving Show

evalStmt :: [(String, Integer)] -> Stmt -> [(String, Integer)]
evalStmt env (Assign var expr) = (var, evalExpr env expr) : env

data Program = Empty | Seq Stmt Program
  deriving Show

evalProgram :: Program -> [(String, Integer)]
evalProgram prog = evalProgram' [] prog

evalProgram' :: [(String, Integer)] -> Program -> [(String, Integer)]
evalProgram' env Empty = env
evalProgram' env (Seq s prog) =
  evalProgram' (evalStmt  env s) prog