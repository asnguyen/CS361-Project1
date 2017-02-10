UTEID: asn579;
FIRSTNAME: Anthony;
LASTNAME: Nguyen;
CSACCOUNT: anthony;
EMAIL: asnguyen@utexas.edu;

This program is my basic implementation of the Bell-LaPadula
model of security. The system is SecureSystem. It creates 
subject and object and then use Instruction to parse
the instructions and uses ResourceMonitor to perform the 
instructions. Within the Instruction Class, it takes the 
line and looks at each token and sees if the instruction 
is valid based purely on the number of arguments. 
ResourceMonitor will check the validity of the instruction's
content. If ResourceMonitor finds the instruction valid, it 
will call ObjectMonitor which will perform the action

This program should perform all the requirement minus the extra credit

There are four test cases
Test Case 1

write hal hobj 
read hal 
write lyle lobj 10
read hal lobj 
write lyle hobj 20
write hal lobj 200
read hal hobj
read lyle lobj
read lyle hobj
foo lyle lobj
Hi lyle, This is hal
The missile launch code is 1234567

Results:
Bad Instruction
The current state is:
	lobj has value: 0
	hobj has value: 0
	lyle has recently read: 0
	hal has recently read: 0

Bad Instruction
The current state is:
	lobj has value: 0
	hobj has value: 0
	lyle has recently read: 0
	hal has recently read: 0

lyle writes value of 10 to lobj
The current state is:
	lobj has value: 10
	hobj has value: 0
	lyle has recently read: 0
	hal has recently read: 0

hal reads lobj
The current state is:
	lobj has value: 10
	hobj has value: 0
	lyle has recently read: 0
	hal has recently read: 10

lyle writes value of 20 to hobj
The current state is:
	lobj has value: 10
	hobj has value: 20
	lyle has recently read: 0
	hal has recently read: 10

hal writes value of 200 to lobj
The current state is:
	lobj has value: 10
	hobj has value: 20
	lyle has recently read: 0
	hal has recently read: 10

hal reads hobj
The current state is:
	lobj has value: 10
	hobj has value: 20
	lyle has recently read: 0
	hal has recently read: 20

lyle reads lobj
The current state is:
	lobj has value: 10
	hobj has value: 20
	lyle has recently read: 10
	hal has recently read: 20

lyle reads hobj
The current state is:
	lobj has value: 10
	hobj has value: 20
	lyle has recently read: 0
	hal has recently read: 20

Bad Instruction
The current state is:
	lobj has value: 10
	hobj has value: 20
	lyle has recently read: 0
	hal has recently read: 20

Bad Instruction
The current state is:
	lobj has value: 10
	hobj has value: 20
	lyle has recently read: 0
	hal has recently read: 20

Bad Instruction
The current state is:
	lobj has value: 10
	hobj has value: 20
	lyle has recently read: 0
	hal has recently read: 20

TestCase2

write lyle lobj 1738
write lyle hobj 8371
write hal lobj 0
write hal hobj 1029
read lyle lobj 
read lyle hobj
read hal lobj
read hal hobj

Results:
lyle writes value of 1738 to lobj
The current state is:
	lobj has value: 1738
	hobj has value: 0
	lyle has recently read: 0
	hal has recently read: 0

lyle writes value of 8371 to hobj
The current state is:
	lobj has value: 1738
	hobj has value: 8371
	lyle has recently read: 0
	hal has recently read: 0

hal writes value of 0 to lobj
The current state is:
	lobj has value: 1738
	hobj has value: 8371
	lyle has recently read: 0
	hal has recently read: 0

hal writes value of 1029 to hobj
The current state is:
	lobj has value: 1738
	hobj has value: 1029
	lyle has recently read: 0
	hal has recently read: 0

lyle reads lobj
The current state is:
	lobj has value: 1738
	hobj has value: 1029
	lyle has recently read: 1738
	hal has recently read: 0

lyle reads hobj
The current state is:
	lobj has value: 1738
	hobj has value: 1029
	lyle has recently read: 0
	hal has recently read: 0

hal reads lobj
The current state is:
	lobj has value: 1738
	hobj has value: 1029
	lyle has recently read: 0
	hal has recently read: 1738

hal reads hobj
The current state is:
	lobj has value: 1738
	hobj has value: 1029
	lyle has recently read: 0
	hal has recently read: 1029

TestCase3

write lyle lobj 1111
write lyle hobj 2222
read lyle lobj
read hal hobj
write
write lyle NotObject 123
write lyle lobj oneMillion
write lyle lobj
write monkey lobj 777
write lyle lobj 1234 1234
read
read hal NotObject
read NotSubject lobj
read hal lobj 89 

Results
lyle writes value of 1111 to lobj
The current state is:
	lobj has value: 1111
	hobj has value: 0
	lyle has recently read: 0
	hal has recently read: 0

lyle writes value of 2222 to hobj
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 0
	hal has recently read: 0

lyle reads lobj
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 0

hal reads hobj
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

TestCase4

write lyle lobj 1111
write lyle hobj 2222
read lyle lobj
read hal hobj
write
write lyle NotObject 123
write lyle lobj oneMillion
write lyle lobj
write monkey lobj 777
write lyle lobj 1234 1234
read
read hal NotObject
read NotSubject lobj
read hal lobj 89 
write lyle lobj 1738
write lyle hobj 8371
write hal lobj 0
write hal hobj 1029
read lyle lobj

Results
lyle writes value of 1111 to lobj
The current state is:
	lobj has value: 1111
	hobj has value: 0
	lyle has recently read: 0
	hal has recently read: 0

lyle writes value of 2222 to hobj
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 0
	hal has recently read: 0

lyle reads lobj
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 0

hal reads hobj
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

Bad Instruction
The current state is:
	lobj has value: 1111
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

lyle writes value of 1738 to lobj
The current state is:
	lobj has value: 1738
	hobj has value: 2222
	lyle has recently read: 1111
	hal has recently read: 2222

lyle writes value of 8371 to hobj
The current state is:
	lobj has value: 1738
	hobj has value: 8371
	lyle has recently read: 1111
	hal has recently read: 2222

hal writes value of 0 to lobj
The current state is:
	lobj has value: 1738
	hobj has value: 8371
	lyle has recently read: 1111
	hal has recently read: 2222

hal writes value of 1029 to hobj
The current state is:
	lobj has value: 1738
	hobj has value: 1029
	lyle has recently read: 1111
	hal has recently read: 2222

lyle reads lobj
The current state is:
	lobj has value: 1738
	hobj has value: 1029
	lyle has recently read: 1738
	hal has recently read: 2222


























