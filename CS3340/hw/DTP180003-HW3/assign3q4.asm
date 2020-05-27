# Drew Pulliam
# DTP180003
# 3340.004
# HW 3 Question 4

	.data
cityNum:.word 2
week:	.word 7
input:	.asciiz "Enter all temperature for a week of first city and then second city. \n"
output:	.asciiz "\n\nDisplaying Values:\n"
city:	.asciiz "City  "
day:	.asciiz ", Day "
colon:	.asciiz " : "
equals:	.asciiz " = "
temp:	.word 0
	
	.text
main: 	lw $s0,cityNum		# $s0 = CITY = 2
	lw $s1,week		# $s1 = WEEK = 7
	la $a0,input		# print input
	li $v0,4
	syscall
	li $t0,0		# i = 0
inFor1:	bge $t0,$s0,display	# if i >= CITY, display
	addi $t0,$t0,1		# ++i
	li $t1,0		# j = 0
inFor2:	bge $t1,$s1,inFor1	# if j >= WEEK, do next city
	addi $t1,$t1,1		# ++j
	la $a0,city		# print city
	li $v0,4
	syscall
	move $a0,$t0		# print i + 1
	li $v0,1
	syscall
	la $a0,day		# print day
	li $v0,4
	syscall
	move $a0,$t1		# print j + 1
	li $v0,1
	syscall
	la $a0,colon		# print colon
	li $v0,4
	syscall
	li $v0,5		# user input temp
	syscall
	move $t6,$v0		# $t6 = user input
	addi $t2,$t0,-1		# $t2 = i + 1 - 1 = i
	addi $t3,$t1,-1		# $t3 = j + 1 - 1 = j
	mul $t2,$t2,4
	mul $t3,$t3,4
	mul $t2,$t2,$s1		# i * WEEK to get 2d array coord
	add $t4,$t2,$t3		# array coord = i * WEEK + j
	la $t5,temp
	add $t5,$t5,$t4		# $t5 = &temp[i][j]
	sw $t6,($t5)		# temp[i][j] = user input temp
	j inFor2
display:la $a0,output		# print output
	li $v0,4
	syscall
	li $t0,0		# i = 0
outFor1:bge $t0,$s0,end	# if i >= CITY, display
	addi $t0,$t0,1		# ++i
	li $t1,0		# j = 0
outFor2:bge $t1,$s1,outFor1	# if j >= WEEK, do next city
	addi $t1,$t1,1		# ++j
	la $a0,city		# print city
	li $v0,4
	syscall
	move $a0,$t0		# print i + 1
	li $v0,1
	syscall
	la $a0,day		# print day
	li $v0,4
	syscall
	move $a0,$t1		# print j + 1
	li $v0,1
	syscall
	la $a0,equals		# print equals
	li $v0,4
	syscall
	addi $t2,$t0,-1		# $t2 = i + 1 - 1 = i
	addi $t3,$t1,-1		# $t3 = j + 1 - 1 = j
	mul $t2,$t2,4		# mul by 4 for byte addressing
	mul $t3,$t3,4		# mul by 4 for byte addressing
	mul $t2,$t2,$s1		# i * WEEK to get 2d array coord
	add $t4,$t2,$t3		# array coord = i * WEEK + j
	la $t5,temp		# &temp[0][0]
	add $t5,$t5,$t4		# $t5 = &temp[i][j]
	lw $a0,($t5)		# print temp[i][j]
	li $v0,1
	syscall
	li $v0,11		# print "\n"
	li $a0,10
	syscall
	j outFor2
end:	li $v0,10		# end program
	syscall
