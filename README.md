# ChatRoom

Create a chatroom with Java

项目配置：IntelliJ（jdk11），MySQL5

#### 2022.2.13 Version1

此版本实现的功能有：本机上的两个客户端可以随意接收发送信息，并将两者发送的信息保存在MySQL数据库当中

此版本使用到的知识有：**简单的**java基础，多线程，JDBC连接，MySQL，Java服务器编程，极少的计算机网络知识

未来版本展望：制作UI界面，可以任意添加客户端数量

项目结构：

​		src目录下的三个目录分别归属客户端，MySQL数据库和服务器。

​		Server目录中TCPServer包含了主方法，ServerThread封装了服务器功能。在ServerThread中让服务器的两个线程同时监听两个客户端，将监听内容存入数据库相应表中，再将内容输出给另一个客户端以完成通信。

​		Client目录包含了两个客户端目录，以及客户端的读线程和写线程。先打开服务器端后，依次打开两个客户端。

​		MySQL目录下InsertMessage封装了插入方法，将服务器监听到的内容存入数据库表中

