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

	sort.Ints(list1)
	sort.Ints(list2)

	result := 0

	for i := 0; i < len(lines); i++ {
		result += abs(list1[i] - list2[i])
	}

	println(result)

}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
