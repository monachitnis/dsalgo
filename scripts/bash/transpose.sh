#!/bin/bash
matrix=()
rows=0
cols=0
file=$1
while read p;do
  arr=($p)
  cols=${#arr[@]}
  matrix+=($p)
  ((rows++))
done <$file

for ((col=0;col<$cols;col++)); 
do
  for ((row=0;row<$rows;row++));
  do
    if [ $row -lt $((rows-1)) ]
    then
      printf "%s " ${matrix[$((cols * row + col))]}
    else
      printf "%s" ${matrix[$((cols * row + col))]}
    fi 
  done
  if [ $col -lt $((cols-1)) ]
  then
    printf "\n"
  fi
done


