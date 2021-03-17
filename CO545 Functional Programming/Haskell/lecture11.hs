{-
Lecture 11
Lambda Calculus, Curry-Howard correspondece, Simply-typed lambda
-}

--p1 Lambda Calculus

{-
syntax:
variables   | functions         | function application

Haskell
t, t' :: x  | x -> t            | t t'

Erlang
t, t' ::= x | fun (X) => t end | t (t')

Java
t,t'  ::= x | x -> t           | t.apply(t)
-}

-- λx.x = x
id = \x -> x
--λx.λy.x
const = \x -> \y -> x
--λg.λf.λx.g(fx)
comp = \g -> \f -> \x -> g(f x)


{- Rules of defining relationship between t and t'
(t ----> t') " t can make one step to become t' "

1. The Beta Rule
  (λx.t)t'  ---> t[t'/x]
  (λx.x+x)2 ---> 2+2

2. Inductive Rule
     t1 ---> t'1
  -----------------
  t1 t2 ---> t'1 t2

3. Evaluate on the Right
     t2 ---> t'2
  -----------------
  t1 t2 ---> t'1 t'2

4. Evaluate inside λ
       t -> t'
  -----------------
    λx.t -> λx.t'

    (λf.λy.f(fy))(λx.x)
---> λy.(λx.x)((λx.x)y)
---> λy.(λx.x)y
---> λy.y (Normal Form, when you cant reduce it anymore)
-}

--p2 Curry-Howard correspondence

{-
 e1 :: s -> t    e2 :: s
 -----------------------
        e1 e2 :: t

kinda looks like
if we know that s is true, and s implies t, then t is true as well. Modus Ponens (Implication Elimination)

Curry-Howard correspondence/isomorphism

Typed lambda calculus   |   Propositional logic
================================================
        types           |         propositions
      programs          |            proofs
================================================

e1 :: s e2 :: t         |           s   t
---------------         |          --------
(e1,e2) :: (s,t)        |           s ^ t

                        |
  e :: (s,t)            |           s ^ t
  ----------            |          ------- ^ elim1
  fst e :: s            |             s


   e :: (s,t)           |           s ^ t
  ------------          |          ------- ^ eilm2
   snd e :: t           |             t


 e1 :: s -> t  e2 :: s  |           s -> t  s
----------------------- |          ----------- elim
       e1 e2 ::t        |               t
-}


--p3 simply typed lambda calculus
{-
Typing Rules

1. Variables
----------
Г,x |- x:A

2. Inductive rule
Г |- t:A->B   Г |- t':A
-----------------------   ->elim (modus ponens)
      Г |- t t':B

3.
     Г,x: A |- t:B
----------------------    ->intro (implication introduction)
  Г |- λ(x:A).t:A->B

A -> ((A -> B) ->B)
-}


