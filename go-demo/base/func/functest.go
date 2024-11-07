package main

import (
	"fmt"
)

func main() {
	str1, str2 := test("test")

	println(str1)
	println(str2)

	x := 3

	fmt.Println("x = ", x) // 应该输出 "x = 3"

	//x1 := add1(x) //调用add1(x)
	x1 := add2(&x) //传入地址

	fmt.Println("x+1 = ", x1) // 应该输出"x+1 = 4"
	fmt.Println("x = ", x)    // 应该输出"x = 3"

}

func test(s string) (string, string) {
	println(s)
	return "cccc", "222222"
}

func add1(a int) int {
	a = a + 1 // 我们改变了a的值
	return a  //返回一个新值
}

func add2(a *int) int { // 请注意，
	fmt.Println("地址", a)
	fmt.Println(*a)
	*a = *a + 1 // 修改了a的值
	return *a   // 返回新值
}
