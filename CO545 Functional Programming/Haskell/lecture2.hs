-- lecture 2

x:: Integer
x = 42

isZero :: Integer -> Bool
isZero 0 = True
isZero _ = False

notZero x = myNot(isZero x)

myNot :: Bool -> Bool
myNot False = True
myNot True = False

myAnd :: Bool -> Bool -> Bool
myAnd True True = True
myAnd _ _ = False

myOr :: Bool -> Bool -> Bool
myOr False False = False
myOr _ _ = True

--lecsem
myIf :: Bool -> Integer -> Integer -> Integer
myIf True x y = x
myIf False x y = y


-- factorial

fact :: Integer -> Integer
fact 0 = 1

fact n | n > 0 = n * fact(n-1)

mySum :: Integer -> Integer
mySum 0 = 0
mySum n | n > 0 = mySum (n-1) + n
mySum n | n < 0 = mySum (n+1) + n
