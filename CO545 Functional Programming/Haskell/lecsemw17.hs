

data Bool3 = Fals | Tru | Mebe
  deriving Show

and3 :: Bool3 -> Bool3 -> Bool3
and3 Tru Tru  = Tru
and3 Tru Fals = Mebe
and3 Mebe _   = Mebe
and3 _ Mebe   = Mebe
and3 _ _      = Fals



myTail :: [a] -> [a]
myTail []     = Nothing
myTail (x:xs) = Just xs

fromMaybe :: Maybe a -> a -> a
