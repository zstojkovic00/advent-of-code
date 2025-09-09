package main

import (
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {

	// data, err := os.ReadFile("day1/test.txt")
	data, err := os.ReadFile("day1/input.txt")

	if err != nil {
		pwd, _ := os.Getwd()
		fmt.Printf("%s\n", pwd)
		fmt.Println(err)
		panic("Could not read test file")
	}

	lines := strings.Split(string(data), "\n")

	list1 := make([]int, 0, len(lines))
	list2 := make([]int, 0, len(lines))

	for _, line := range lines {
		parts := strings.Split(line, "   ")

		if len(parts) != 2 {
			panic("Invalid file format")
		}

		num1, err1 := strconv.Atoi(parts[0])
		num2, err2 := strconv.Atoi(parts[1])

		if err1 != nil || err2 != nil {
			panic("Invalid file format")
		}

		list1 = append(list1, num1)
		list2 = append(list2, num2)
	}

	result1 := historianHysteria1(list1, list2)
	println(result1)
	result2 := historianHysteria2(list1, list2)
	println(result2)
}

func historianHysteria2(list1 []int, list2 []int) int {
	similarityScore := 0

	for i := 0; i < len(list1); i++ {
		times := 0
		for j := 0; j < len(list2); j++ {
			if list1[i] == list2[j] {
				times++
			}
		}
		similarityScore += list1[i] * times
	}

	return similarityScore
}

func historianHysteria1(list1 []int, list2 []int) int {
	newList1 := make([]int, len(list1))
	newList2 := make([]int, len(list2))
	copy(newList1, list1)
	copy(newList2, list2)

	sort.Ints(newList1)
	sort.Ints(newList2)

	result := 0

	for i := 0; i < len(newList1); i++ {
		result += abs(newList1[i] - newList2[i])
	}

	return result
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
