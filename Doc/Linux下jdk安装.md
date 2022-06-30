### Linux下jdk安装

①下载jdk

​	wget 下载地址   (在官网下载，趁AuthParam没有过期，将地址拷贝过来)

②新建文件夹     /usr/java

③解压下载好的jdk到/usr/java      

​	tar -xzvf file.tar.gz      解压tar.gz

④配置环境变量

​	vi /etc/profile

​	在文件末尾加上

	export JAVA_HOME=/usr/java/jdk1.8.0_231
	PATH=$JAVA_HOME/bin:$PATH
	export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
⑤使配置生效

​	source /etc/profile

⑥检查是否安装成功

​	java -version

###运行jar
nohup java -jar helloworld-0.0.1.jar >out.txt &           nohup后台启动   
//这种方法会把日志文件输入到你指定的文件中，没有则会自动创建。进程会在后台运行。
####对nohup: ignoring input and redirecting stderr to stdout的解释
翻译：忽略输入，重定向标准错误到标准输出
如果不想看到这个提示，可以添加  2>&1  改为如下
nohup java -jar helloworld-0.0.1.jar >out.txt 2>&1 &      
2代表标准错误，1代表标准输出，0代表标准输入，2>&1代表将标准输出重定向到标准输出中
###停止jar
kill -9 pid