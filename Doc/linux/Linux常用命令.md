# Linux常用命令

## tar

```
打包
tar -cvf xxx.tar aaa bbb ccc.log
解打包
tar -xvf xxx.tar

v 显示过程
f 指定文件名
c 将多个文件或目录打包
x 对tar包进行解压操作
```

## gzip

```
打包
tar -zcvf
解打包
tar -zxvf
```

## 查看网络情况

```
ip address
ifconfig
ifconfig -a
ifconfig eth0
其中eth0网卡为本地网卡
```

## mkdir

```
创建多个目录
mkdir a b c
创建多级目录
mkdir -p a/b/c
```

## 文件传输

sftp、scp、ftp

```
sftp常用指令
上传
mkdir aaa
put -r aaa
下载
get -r aaa
```

## 查看端口占用

```
netstat -anp | grep 8080
```

## du

```
查询当前目录总大小
du -sh
查看当前目录下各文件、文件夹的大小
du -h --max-depth=1
```

