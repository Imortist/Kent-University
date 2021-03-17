-module(class8).
-compile(export-all).


power(X,0) -> 1;
power(X,-1) -> 1/X;
power(X,N) -> if N rem 2==0 -> power(X*X, N bsr 1);
                N rem 2/=0 -> X*power(X*X, N bsr 1)
              end.

square (X)-> if X == 0 -> 0;
X < 0 -> square(-X);
X > 0 -> square(X-1) + X - X-1
end.

take(0,_) -> [];
take(_,[]) -> [];
take(N, [H|T]) when N > 0 -> [H|take(N-1,T)].

matrixify([]) -> [[]];
matrixify([nl|T]) -> [[]|matrixify(T)];
matrixify([A|T]) -> [L|U] = matrixify(T), [[A|L]|U].
