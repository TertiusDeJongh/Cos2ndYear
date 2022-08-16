#!/bin/bash

yasm -f elf64 -g dwarf2 -l hello.lst assembly/hello.asm
ld -o hello assembly/hello.o

for((ii = 0; ii<50; ii++)); do
	start=$(date +%s%N)
		for((i =0; i<500; i++)); do
			./assembly/hello
		done
	end=$(date +%s%N)
	echo
	#this where we push into an array
	AsResult=("${AsResult[@]}" $(($end - $start)))
	#echo elapsed time: $(($end - $start)) ns
done

 RESULT = 0

for i in "${AsResult[@]}"; do
	echo $i >> assembly/results.txt
	let RESULT+=$i
done
echo total runtime in ns: $RESULT >> assembly/results.txt
echo the average in ns: $((RESULT/25000)) >> assembly/results.txt

#C++ here
echo
 g++ C++/hello.cpp
for((ii = 0; ii<50; ii++)); do
	start=$(date +%s%N)
		for((i =0; i<500; i++)); do
			./C++/a.out
		done
	end=$(date +%s%N)
	echo
	#this where we push into an array
	CResult=("${CResult[@]}" $(($end - $start)))
	#echo elapsed time: $(($end - $start)) ns
done

 RESULTC = 0

for i in "${CResult[@]}"; do
	echo $i >> C++/results.txt
	let RESULTC+=$i
done
echo total runtime in ns: $RESULTC >> C++/results.txt
echo the average in ns: $((RESULTC/25000))>> C++/results.txt

#python here
echo
for((ii = 0; ii<50; ii++)); do
	start=$(date +%s%N)
		for((i =0; i<500; i++)); do
			python Python/hello.py
		done
	end=$(date +%s%N)
	echo
	#this where we push into an array
	PResult=("${PResult[@]}" $(($end - $start)))
	#echo elapsed time: $(($end - $start)) ns
done

 RESULTP = 0

for i in "${PResult[@]}"; do
	echo $i >> Python/results.txt
	let RESULTP+=$i
done
echo total runtime in ns: $RESULTP >> Python/results.txt
echo the average in ns: $((RESULTP/25000)) >> Python/results.txt

#java here
echo
javac Java/hello.java
for((ii = 0; ii<50; ii++)); do
	start=$(date +%s%N)
		for((i =0; i<500; i++)); do
			java -cp Java hello
		done
	end=$(date +%s%N)
	echo
	#this where we push into an array
	JResult=("${JResult[@]}" $(($end - $start)))
	#echo elapsed time: $(($end - $start)) ns
done

 RESULTJ = 0

for i in "${JResult[@]}"; do
	echo $i >> Java/results.txt
	let RESULTJ+=$i
done
echo total runtime in ns: $RESULTJ >> Java/results.txt
echo the average in ns: $((RESULTJ/25000)) >> Java/results.txt

echo NICE WE ARE DONE HERE
echo EVERY DIRECTORY SHOULD HOLD A FILE CALLED results.txt
echo GO LOOK IN IT
