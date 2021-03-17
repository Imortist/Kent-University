-- Imperative programming, I/O, Mutation
-- stack script --resolver lts-14,18

import Data.IORef

main :: IO ()
main = do
  putStrLn "What?"
  inp <- getLine
  putStrLn $ "Ok then" ++ inp

{-
main :: IO()
main =
  putStrLn "Say something nice to me" >> (getLine >>= (\inp -> putStrLn $ "well then " ++ inp ++ " too"))
  -- getLine :: IO String
  -- inp :: String
  -- (\inp -> putStrLn inp) :: String -> IO()

-}

{- FUGLY
echo :: IO String
echo = getLine >>= (\inp -> putStrLn inp >> return inp)
-}

--p2 DO notations

-- EVERY LINE OF DO HAS TO BE AN io COMPUTATION
echo :: IO String
echo = do
  inp <- getLine
  putStrLn inp
  return inp

-- putStrLn inp :: IO ()
-- inp :: String
-- return inp :: IO String
-- putStrLn in >> return inp :: IO String

dubEcho :: IO ()
dubEcho = do
  inp <- getLine
  putStrLn inp
  putStrLn inp

--p3 Mutable references

inc :: IORef Integer -> IO ()
inc ref = do
  x <- readIORef ref
  writeIORef ref (x+1)

mutationexample = do
  ref <- newIORef 42
  inc ref
  y <- readIORef ref
  putStrLn $ show y

--MONADS

{-
class Monad m where
  return :: a -> m a
  (>>=)  :: m a -> (a -> m b) -> mb
-}