This project is split into parts a/ and b/,
for practical reasons: the simulator can't cope
with the huge ememories in part b/ if they are all
implemented explicitly in HDL. So, in part b/
you should use the built-in RAM64 chip... hence
why it is split into a separate directory.

Section 3.5 of the book explains all this.

-Stephen
