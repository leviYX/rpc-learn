# 一、RPC简介

RPC的全称是Remote Procudure Call，也就是远程服务调用，其实本质是一种跨进程的调用(在java的世界中，其实就是跨JVM)，他的定义就尽于此。此外所有的符合这个定义的服务调用都是RPC调用，包括http实现的，包括其他技术实现的。他是一种概念，而不是一种具体的技术,只是一个方法论。

RPC在微服务这个概念中被提到的很多，因为微服务的体系下，会出现很多的服务之间的接口交互，此时就符合关于跨进程的调用，也就是RPC。各家实现RPC的方法不尽相同，比如cloud的fegin这就是一种实现。

但是总是要调用方发给服务方的一个完整的方法信息，url 名字，参数，返回值，这样服务端才能找到你要找的目标方法。

# 二、Hessian Rpc

~~~markdown
1. Hessian的特点1
	1)、他是纯粹的RPC，只解决RPC中的4个核心问题(1、网络通信 2、协议 3、序列化 4、远程调用代理）
	2)、java语言实现
	3)、HessianRPC已经落伍了，但是其序列化方式依然被Dubbo所使用，但是Dubbo用的Hessian是阿里重新实现的(Hessain Lite)，Dubbo中默认启动就是这个。
~~~

## 1、Hessian Rpc的概念

~~~markdown
1. Resin服务器的伴生产品。
2. 基于Java编程语言设计的RPC框架，只支持java语言(现在很多都已经跨语言了)，他的服务调用者和服务的提供者都必须是java开发的。
3. 在语言层面的序列化协议是直接序列化为二进制，开销很小。虽然最后都是在网络传输字节流都是二进制的，但是如果你在语言层面就序列化为二进制的，网络传输肯定是更节省的。
4. 官网地址 ：http://hessian.caucho.com/doc/
~~~

## 2、Hessian Rpc的设计思想

~~~markdown
# 调用方：
	1、HessianProxyFactory来做服务代理，不用你自己去实现，但是要实现服务端的这个接口代理需要两个要素，一个是这个接口类，一个是请求哪个服务具体的url。
	2、因为你调用方也要这个service接口来实现代理，所以必须做成公共模块来共同引用。
# 被调用方：
	1、服务器：tomcat 或者resin
	2、创建服务：其实就是我们原来开发的service，但是他必须要有接口和实现类，就是类似这种OrderService 和 OrderServiceImpl这样的结构，因为Hessian的远程代理是通过JDK的代理创建的，必须要基于接口，他比较早，所以没选择CGLIB。
	数据类型定义时候，对象类型必须实现Serliazble接口，因为要进行二进制的序列化。
	3、服务的发布
		让调用者可以知道我提供了那些服务，以及如何访问这些服务，是要在web.xml中配置HessianServlet，类似当初springmvc配置servlet的样子。
		因为协议都是二进制的传输，所以必须是post请求。
~~~


thrift官方网址:https://thrift.apache.org/
mac安装thrift:brew install thrift
windows安装thrift:URL_ADDRESSwindows安装thrift:https://thrift.apache.org/docs/install/windows
验证安装:thrift --version 
java开发的thrift安装包:
~~~markdown
<dependency>
  <groupId>org.apache.thrift</groupId>
  <artifactId>libthrift</artifactId>
  <version>0.22.0</version>
</dependency>
~~~

idea开发需要安装thrift插件:提供语法，代码补全，语法检查等功能。
~~~markdown
File -> Settings -> Plugins -> Browse repositories -> search thrift -> install
~~~
我们需要在thrift文件中定义相关的信息，然后通过thrift命令生成java代码。
~~~markdown
thrift -r --gen java hello.thrift
~~~
具体的语法如下:
~~~markdown
我们的目的是通过在thrift文件中定义相关的信息，然后通过thrift命令生成各种语言的代码，这里我们以java为例。
包路径：
    namespace java com.levi.thrift // 定义java语言的包路径
    namespace go levi.thrift // 定义go语言的包路径
    namespace py levi.thrift // 定义python语言的包路径
    namespace php levi.thrift // 定义php语言的包路径
    namespace csharp levi.thrift // 定义c#语言的包路径
    namespace cpp levi.thrift // 定义c++语言的包路径
    namespace rb levi.thrift // 定义ruby语言的包路径
    namespace perl levi.thrift // 定义perl语言的包路径
    namespace nodejs levi.thrift // 定义nodejs语言的包路径
    namespace lua levi.thrift // 定义lua语言的包路径
    namespace dart levi.thrift // 定义dart语言的包路径
    namespace rust levi.thrift // 定义rust语言的包路径
    namespace swift levi.thrift // 定义swift语言的包路径
    namespace kotlin levi.thrift // 定义kotlin语言的包路径
    namespace typescript levi.thrift // 定义typescript语言的包路径

基本类型:
    i8: 有符号8位整数类型  在java中就是byte 下面都是java的类型，其余的语言有自己的
    i16: 有符号16位整数类型 在java中就是short
    i32: 有符号32位整数类型 在java中就是int
    i64: 有符号64位整数类型 在java中就是long
    double: 64位浮点数类型 在java中就是double
    string: 字符串类型 在java中就是String 在所有语言中就是UTF-8编码的字符串 可以是单引号也可以是双引号
    binary: 二进制类型 在java中就是byte[]
    void: 空类型 在java中就是void

集合类型:
    list<T>  有序可重复类型 在java中就是java.util.List<T>
    set<T>   无序不可重复类型 在java中就是java.util.Set<T>
    map<K,V> 键值对类型 在java中就是java.util.Map<K,V>
    举一个map的例子，如果你想生成一个Map<Integer,String>类型的代码，就要这样声明。
    map<i32,string> sex = {1:"男",2:"女"}
    集合类型是这样，list<i32> age = [1,2,3,4,5]

struct 自定义对象，在java中就是实体类，类似于c的结构体：
    struct User {
        1:i32 id,
        2:string name,
        3:i32 age
    }
    1、举一个struct的例子，如果你想生成一个User类型的代码，就要这样声明。里面有序号，类型，属性名称。
    2、struct的类不能继承，成员与成员之间的分割可以是逗号，也可以是分号。
    3、结构体里面的每一个字段都要进行编号，从1开始。
    4、结构是变量类型，变量名。
    5、还有一种类型，叫做optionl，就是可选类型，就是可以为空的类型，就是在序列化的时候是可选的，没有值就不序列化，有就序列化。
        默认为每一个成员都加入的关键字，可以不写。我上面就没写，其实默认就是。你妹提供默认值，他就不做序列化。
    6、还有一个关键字叫required，就是必须的，就是在序列化的时候是必须的，没有值就报错。和optionl相反。
        你妹提供默认值，他就报错。
    举个例子：其中email就是可选的，name就是必须要有值的。
    namespace java com.levi.entity
    struct User {
        required string name,
        2: i32 age
        optional string email = '123.qq.com'
    }

enum 枚举类型，在java中就是枚举类：枚举以逗号分隔，并且不支持嵌套(枚举内部不能再定义枚举)
    而且枚举中的整形只能是i32
    namespace java com.levi.entity
    enum Sex {
        MALE=1,
        FEMALE=2
    }

异常类型：
    exception 异常类型，在java中就是异常类：异常以逗号分隔也可以不分割，啥也不加都行，并且不支持嵌套(异常内部不能再定义异常)
    而且异常中的整形只能是i32
    namespace java com.levi.entity
    exception UserException {
        1: i32 code, 
        2: string message
    }
    举个例子：
~~~