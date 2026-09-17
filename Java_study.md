# 时间顺序

## 2026.09.17

- 经过一段时间学习，明白了什么叫做面向对象的编程了，所有系统函数都在类似struct的类里，使用时有的需要先new一个对象

# 学习顺序

> 写在这里，免得忘记

## 输入

`Scanner 对象=new Scanner(System.in);`

`sc.nextXXX()`

> 为什么没有scanf

##　输出

快捷键`sout`

更喜欢`printf`规范一些

## switch（和c++有差别）

用->执行单条语句

若->搭配有返回值的用法，在多条语句的{}内要用`yield X;`返回值

## 数组

`类型[] X=new int[N];`

数组存的是地址，相当于c++中带&，引用方法会一同更改

## IO流

file可以获取文件信息，判断类型，创建删除文件/文件夹

但是不能读写文件内容

IO流分为输入输出流，字节字符流

#### 字节-输入：`FileInputStream`

`read()`空参，但是有int返回值，读不到就返回-1

所以可以while(read()!=-1)



---

#### 字节-输出：`FileOutputStream`

> 创建对象

write后要close释放资源

若文件不存在会创建，但是父文件夹必须存在

若文件已经存在，则清空文件

> 写数据

write参数为int，但是实际上是Ascll码

write格式：

- void write(int b)
- void write(byte[] b)
- void write(byte[] b,int off,int len)

`其他类型的数组可以getbyte()生成`

> 续写

创建对象时加一个参数true

> 释放资源

不释放资源那么vscode会占用改文件

#### 拷贝

创建输入流

创建输出流（拷贝地址）

write(read())

> 很慢怎么办

`read(byte[] buffer)`返回值为读取的数组长度

数组大小可以是$1024*1024*5$

write记得规定长度为len

## try-catch

```java
try/*(IO流，无需关闭)*/{

}catch(/*报错类型*/){

}finally{
	//一定会执行
}
```

