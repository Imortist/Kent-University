-- class 6 notes

class Parsable t where
  parse :: String -> Maybe t

instance Parsable Bool where
  parse "True"  = Just True
  parse "False" = Just False
  parse _       = Nothing

parseEquals s x = parse s == Just x

putStrLn :: String -> IO ()
getLine :: IO String

-- Define a function that reads a string input and uses
-- the parse function to parse it into a MaybeBool(using the instance written
-- during the group discussion). If the input is invalid show an appropriate
-- message. If the input is valid and True then output"Hello world"
-- to standard out, otherwise do nothing.

boolToString :: IO String -> Maybe b -> IO ()