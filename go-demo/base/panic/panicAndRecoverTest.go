package main

import (
	"fmt"
)

/*
Panic和Recover
Go没有像Java那样的异常机制，它不能抛出异常，而是使用了panic和recover机制。一定要记住，你应当把
它作为最后的手段来使用，也就是说，你的代码中应当没有，或者很少有panic的东西。这是个强大的工具，请明智地使用它。那么，我们应该如何使用它呢？

Panic

是一个内建函数，可以中断原有的控制流程，进入一个令人恐慌的流程中。当函数F调用pani
c，函数F的执行被中断，但是F中的延迟函数会正常执行，然后F返回到调用它的地方。在调用的地方，F的行为就像调用了panic。
这一过程继续向上，直到发生panic的goroutine中所有调用的函数返回，此时程序退出。恐慌可以直接调用panic产生。也可以由运行时错误产生，例如访问越界的数组。

Recover

是一个内建的函数，可以让进入令人恐慌的流程中的goroutine恢复过来。recover仅在延
迟函数中有效。在正常的执行过程中，调用recover会返回nil，并且没有其它任何效果。如果当前的goroutine陷入恐慌，
调用recover可以捕获到panic的输入值，并且恢复正常的执行。
*/
func recoverName() {
	if r := recover(); r != nil {
		fmt.Println("recovered from ", r)
	}
}

func fullName(firstName *string, lastName *string) {
	defer recoverName()
	if firstName == nil {
		panic("runtime error: first name cannot be nil")
	}
	if lastName == nil {
		//这里触发后 会触发 defer recoverName函数 然后下面代码不会执行了
		panic("runtime error: last name cannot be nil")
	}
	//下面并不会执行
	fmt.Printf("%s %s\n", *firstName, *lastName)
	fmt.Println("returned normally from fullName")
}

func main() {
	defer fmt.Println("deferred call in main")
	firstName := "Elon"
	//这里不会报错 因为 recoverName函数处理了 panic
	fullName(&firstName, nil)
	fmt.Println("returned normally from main")
}
