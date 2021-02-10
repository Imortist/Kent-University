// The program input is at R0 and R1
// Program calculates quotient(R3) and the remainder(R4). R3 n R4 are locations.
// We assume R0 and R1 holds positive numbers;

@R3
M=0
@R4
M=0

@R0
D = M

@END
D, JEQ

@store
M=D

(LOOP)
@R1
D = D-M
@REMAINDER
D, JLT

@R3
M = M+1
@EVENLY
D, JEQ
@LOOP
0,JMP

(REMAINDER)
@R1
D=D+M
@R4
M=D
(EVENLY)
@store
D=M
@R0
M=D

(END)
@END
0,JMP