Generalised "Tower of Hanoi" problem is identified by the following 4 parameters:

	1. the number n(>= 1) of discs.
	2. the number t(>= 3) of towers(pegs).
	3. the source tower s.
	4. the destination tower d.

Task 1 Tower of Hanoi Solver:
	Write a program that takes as input an instance (n,t,s,d) of the generalised tower 
	of Hanoi problem and provide as output a sequence of disc moves between towers.
	The disc moves should be such that the following will always hold:
	"For twho discs labelled 'i' and 'j' stacked at the same tower, if i<j, then disc 'i' 
	will be above disc 'j' in the stack."

	Optimization of the solution is not requred
	Program should be executable via command line.
	The output should be provided in 2 forms simultaneously.
		1. On screen i.e.: "Move disk 1 from T1 to T4"
		2. In a file: 
			a. The output shoudl be arranged in rows / lines containing 
			integers separated by a space or a tab.
		
			b. The first row should have the following four integers
		   	number_of_discs number_of_towers source_tower destination_tower
		
			c. Every subsequent row in the file should have three integers
		   	disc_number source_tower destination_tower

Task 2 Correctness checker for generalised Tower of Hanoi problem:
	Write a program that will take as input a file containing a sequence of moves for an
	instance (n,t,s,d) of the generalised tower of Hanoi problem. You may assume that the
	input file is in a format as mentioned above for task 1. The program should be executable
	via command line. Optimization of the solution is not requred.
	The program will first read the values (n,t,s,d) from the first line/row of the input file
	to identify the problem instance. Then it will read all the moves sequentially and check if they
	are all valid. The program should check that at least the following conditions are true:
		
		1. If disc_number being moved is indeed at the top of source_tower
		2. If disc_number being moved is smaller than the disc on top of destination_tower
		3. If after executing all the moves given in the input file, all towers except 
		   destination_tower are empty.


Sample output fro ab123_ToH_n6_t5_s1_d2.txt:

	The status of all the towers at the start is as follows:
	Tower 1: [6,5,4,3,2,1]
	Tower 2: []
	Tower 3: []
	Tower 4: []
	Tower 5: []

	Move disc 1 from tower 1 to tower 4
	Before the move:
	Source tower 1: [6,5,4,3,2,1]
	Destination tower 4 : []
	After the move:
	Source tower 1: [6,5,4,3,2]
	Destination tower 4: [1]

	...

If error is encountered:
	
	...

	Move: disc 4 from tower 1 to tower 3
	Before the move:
	Source tower 1: [6,5,4]
	Destination tower 3: [3,2,1]
	Move error: Destination tower: [3,2,1] has a smaller disc than 4 on top

	The status of all the towers is as follows:
	Tower 1: [6,5,4]
	Tower 2: []
	Tower 3: [3,2,1]
	Tower 4: []
	Tower 5: []
	
	The sequence of move is incorrect.



	
	
 
