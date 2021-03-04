-- Imperative programming, I/O, Mutation
-- stack script --resolver lts-14,18

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

