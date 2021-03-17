-- hello world (:

x = 6
y = 4
z = x+y


sqr x =
  x^2

quad x =
  sqr x * sqr x

powerSixteen x =
  opt x * opt x
  where
    opt x = quad x * quad x
foo' x =
  case x of
    0 -> 200
    2 -> 200
    _ -> x*400

foo 0 = 100
foo 2 = 200
foo n = n*400
