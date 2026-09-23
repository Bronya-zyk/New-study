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

### StringBuilder

vector版string

`append`添加字符

`tosString`转换为string

`setLength(0)`清空

`delete(a,b)`删除$[a,b)$的字符

`reverse`反转

## Integer

string转int

是一个静态类可以直接调用，不用new对象

错误返回`NumberFormatException`

## Stream流

### List：类似vector数组

| 方法                  | 作用                             |
| --------------------- | -------------------------------- |
| `add(E e)`            | 末尾添加元素                     |
| `add(int index, E e)` | 在下标 index 插入元素            |
| `get(int index)`      | 按下标获取元素                   |
| `set(int index, E e)` | 修改下标位置的元素，返回旧值     |
| `remove(int index)`   | 按下标删除元素，返回被删元素     |
| `remove(Object o)`    | 删除第一个匹配的元素             |
| `size()`              | 返回集合元素个数                 |
| `isEmpty()`           | 判断是否为空                     |
| `contains(Object o)`  | 判断是否包含该元素               |
| `clear()`             | 清空所有元素                     |
| `stream()`            | 转成 Stream 流（你前面写的过滤） |

### set：集合

| 方法                 | 作用                                   |
| -------------------- | -------------------------------------- |
| `add(E e)`           | 添加元素；重复元素添加失败，返回 false |
| `remove(Object o)`   | 删除指定元素                           |
| `size()`             | 元素个数                               |
| `isEmpty()`          | 是否为空                               |
| `contains(Object o)` | 是否包含元素                           |
| `clear()`            | 清空                                   |
| `stream()`           | 转 Stream 流                           |

### map：哈希键值对

| 方法                                  | 作用                                               |
| ------------------------------------- | -------------------------------------------------- |
| `put(K key, V value)`                 | 存入键值对；key 重复会覆盖旧 value                 |
| `get(K key)`                          | 根据 key 获取 value；找不到返回 null               |
| `getOrDefault(K key, V defaultValue)` | 取 key 的值，找不到返回默认值（推荐）              |
| `remove(K key)`                       | 根据 key 删除整组键值对                            |
| `containsKey(K key)`                  | 判断是否存在这个 key                               |
| `containsValue(V value)`              | 判断是否存在这个 value                             |
| `size()`                              | 键值对数量                                         |
| `isEmpty()`                           | 是否为空                                           |
| `clear()`                             | 清空 map                                           |
| `keySet()`                            | 返回所有 key 组成的 Set 集合                       |
| `values()`                            | 返回所有 value 组成的 Collection                   |
| `entrySet()`                          | 返回所有键值对`Map.Entry`的 Set，用于遍历 + Stream |

> `copyof()`  返回一个不可变Map/List/Set

### main

中间

| 方法         | 作用                         | 示例                                                  |
| ------------ | ---------------------------- | ----------------------------------------------------- |
| `filter()`   | 过滤，保留返回 true 的元素   | `.filter(s -> s.getScore() >=60)`                     |
| `map()`      | 映射：把元素转换成另一种类型 | `.map(Student::getName)` Student → String 名字        |
| `sorted()`   | 排序                         | `.sorted(Comparator.comparingInt(Student::getScore))` |
| `distinct()` | 去重（依靠 equals/hashCode） | `.distinct()`                                         |
| `limit(n)`   | 只取前 n 个元素              | `.limit(3)`                                           |
| `skip(n)`    | 跳过前 n 个                  | `.skip(2)`                                            |
| `peek()`     | 遍历查看元素（调试打印）     | `.peek(s->System.out.println(s))`                     |

终止操作

| 方法                                    | 作用                             |
| --------------------------------------- | -------------------------------- |
| `collect(Collectors.toList())`          | 收集成 List（你之前写的）        |
| `collect(Collectors.toSet())`           | 收集成 Set，自动去重             |
| `collect(Collectors.toMap(key,value))`  | 收集成 Map                       |
| `forEach()`                             | 遍历每一个元素                   |
| `count()`                               | 统计元素数量，返回 long          |
| `max() / min()`                         | 找最大 / 最小元素，返回 Optional |
| `sum()`                                 | 求和（需要`mapToInt`转成数值流） |
| `anyMatch() / allMatch() / noneMatch()` | 匹配判断，返回 boolean           |

## 继承与接口

接口弥补了java单继承的不足

多个接口有相同命名，相同参数，且有代码实现的方法，那么会报错

若没有实现，则子类实现即可

父类方法调用优先级大于接口方法

# Integer

List，set，map只支持包装类，所以把int转换为integer

重点：integer会缓存-128~127的数

即：

```
Integer a=100;
Integer b=100;
//a==b->true地址一样

Integer c=200;
Integer d=200;
//c==d->false因为c和d是new的两个对象，两个对象地址不一样
```

# 增强for

语法糖

for(auto x:arr)

注意遍历时不能改变集合

