# Drew Pulliam
# DTP180003
# 3340.004
# HW 3 Question 3

	.data
adultCh:.word 1
childCh:.word 2
seniorCh:.word 3
quitCh:	.word 4
adult:	.word 250
child:	.word 200
senior:	.word 350
menu:	.asciiz "\n\t\tHealth Club Membership Menu\n\n1. Standard Adult Membership\n2. Child Membership\n3. Senior Citizen Membership\n4. Quit the Program\n\nEnter your choice: "
badNum: .asciiz "Please enter a valid menu choice: "
months:	.asciiz "For how many months? "
charges:.asciiz "The total charges are $"
	
	.text
main: 	li $s0,0		# charges = $s0 = 0
	lw $s1,adultCh		# $s1 = adult = 1
	lw $s2,childCh		# $s2 = child = 2
	lw $s3,seniorCh		# $s3 = senior = 3
	lw $s4,quitCh		# $s4 = quit = 4
doLoop:	la $a0,menu		# print menu
	li $v0,4
	syscall
	li $v0,5		# user input integer
	syscall
	move $t0,$v0		# $t0 = choice = user input
	j while2
retry:	la $a0,badNum		# print badNum
	li $v0,4
	syscall
	li $v0,5		# user input integer
	syscall
	move $t0,$v0		# $t0 = choice = user input
while2:	bgt $t0,$s4,retry	# check if choice > quitChoice
	blt $t0,$s1,retry	# check if choice < adultChoice
	beq $t0,$s4,while1	# check if choice == quitChoice
	la $a0,months		# print months
	li $v0,4
	syscall
	li $v0,5		# user input integer
	syscall
	move $t1,$v0		# $t1 = months = user input
	li $t2,0		# $t2 = i = 0
	beq $t0,$s1,pAdult	# case Adult
	beq $t0,$s2,pChild	# case Child
	beq $t0,$s3,pSenior	# case Senior
pAdult:	lw $t3,adult		# set $t3 = approriate cost
	j forLoop		# then j forLoop to count total charges
pChild:	lw $t3,child
	j forLoop
pSenior:lw $t3,senior
	j forLoop
forLoop:bge $t2,$t1,total	# check if i >= months
	addi $t2,$t2,1		# i++
	add $s0,$s0,$t3		# charges += correct amount (depends on switch)
	j forLoop
total:	la $a0,charges		# print charges
	li $v0,4
	syscall
	move $a0,$s0		# print charges num
	li $v0,1
	syscall
	li $v0,11		# print endl
	li $a0,10
	syscall
while1:	bne $t0,$s4,doLoop	# check if choice != quit, then repeat loop
	li $v0,10		# end program
	syscall
