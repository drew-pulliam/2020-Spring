# Drew Pulliam
# DTP180003
# 3340.004
# HW 2 Question 4
	
	.text
main: 	la $t0, str1		# load starting address of str1 into $t0
	la $t2, str2		# load starting address of str2 into $t2
	lb $t1, 0($t0)		# load byte at address $t0 into $t1
	lb $t3, 0($t2)		# load byte at address $t2 into $t3
loop1:	beqz $t1, loop2		# if $t1 == 0, jump to loop2
	addi $t0, $t0, 1	# increment $t0 by 1
	lb $t1, 0($t0)		# load byte at address $t0 into $t1
	j loop1			# jump to loop1
loop2:	beqz $t3, print		# if $t3 == 0, jump to print
	sb $t3, 0($t0)		# store byte $t3 at address $t0
	addi $t2, $t2, 1	# increment $t2 by 1
	lb $t3, 0($t2)		# load byte at address $t2 into $t3
	addi $t0, $t0, 1	# increment $t0 by 1
	j loop2			# jump to loop2
print:	sb $0, 0($t0)		# add null character "\0" to end of string
	li $v0,4		# command to print string
	la $a0,ending		# load string address (ending) into $a0
	syscall			# call system
	la $a0,str1		# load string address (str1) into $a0
	syscall			# call system
	li $v0,10		# command to exit code
	syscall			# call system

	.data
str1:	.asciiz "hello"	
str2:	.asciiz "mars"
ending: .asciiz "\n String after the Concatenate = "
