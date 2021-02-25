-- p1 Type Classes

-- matches one is defined as for all types a
-- which have an implementation of the Eq (equality) class.
matchesOne :: Eq a => (a, a) -> a -> Bool
matchesOne  (x,y) z = (x == z) || (y == z)

data ColoursX = R| G | B | RGB Int Int Int
  deriving Show

data Item = Stick | Spoon

instance Eq ColoursX where
  R == R = True
  G == G = True
  B == B = True
  (RGB r g b) == (RGB r' g' b') = r == r' && g == g' && b == b'
  (RGB 255 0 0) == R = True
  (RGB 0 255 0) == G = True
  (RGB 0 0 255) == B = True
  R == (RGB 255 0 0)  = True
  G == (RGB 0 255 0)  = True
  B == (RGB 0 0 255) = True
  _ == _ = False