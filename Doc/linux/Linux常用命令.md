# Linux常用命令

[TOC]

## 压缩解压

### tar

```
压缩
tar -cvf xxx.tar aaa bbb ccc.log
解压到当前目录
tar -xvf xxx.tar
解压到指定目录
tar -xvf xxx.tar -C /path/to/
调用gzip
tar -zcvf file.tar.gz aaa bbb
tar -zxvf file.tar.gz

v 显示过程
f 指定文件名
c 将多个文件或目录打包
x 对tar包进行解压操作
z 调用gzip
```

### zip

```
打包多个文件
zip target_file.zip file1 file2

打包文件夹
zip -r target_file.zip forder1
 -r 表示将文件夹中的所有文件和子文件夹都压缩进压缩包。
 
 解压到当前目录
 unzip target_file.zip
 
 解压到指定目录
 unzip target_file.zip -d /path/to/dir
```

## 查看网络情况

```
hostname -I
ip address
ifconfig
ifconfig -a
ifconfig eth0
其中eth0网卡为本地网卡
```

## 查看操作系统版本

```
cat /etc/redhat-release
cat /etc/centos-release
hostnamectl
```

## 创建文件夹-mkdir

```
创建多个目录
mkdir a b c
创建多级目录
mkdir -p a/b/c
```

## 查看文件夹大小-du

```
查询当前目录总大小
du -sh
查看当前目录下各文件、文件夹的大小
du -h --max-depth=1
du -sh *
```

## 文件传输

sftp、scp、ftp

### sftp

```
sftp常用指令
默认端口是22
连接服务器
sftp -P 22 root@127.0.0.1
上传
mkdir aaa
put -r aaa
下载
get -r aaa
查看远程服务器内容
ls
查看本地服务器内容
lls
执行本地shell命令
![command]
```

### scp

```
从本地主机传输文件到远程主机
scp [本地文件路径] [用户名]@[远程主机IP地址]:[目标路径]

从远程主机传输文件到本地主机
scp [用户名]@[远程主机IP地址]:[远程文件路径] [本地目标路径]

 -P 22指定端口
 -r 指定目录
```

## 查看端口占用-netstat

```
查看全部端口占用
netstat -tunlp
查看指定端口占用
netstat -tunlp|grep 8080
根据进程id查看进程信息
ps -ef|grep [pid]
```

## 查看系统所有用户

```
more /etc/passwd
```

## curl

```
// 下载文件
curl url -O
// 忽略证书
curl url --insecure
// 指定方法
curl url -X POST
// 添加请求头
curl url -X POST -H "Accept:application/json" -H "Content-Type:application/json"
// 添加请求体
curl url -X POST -H "Accept:application/json" -H "Content-Type:application/json" -d ' {
"password": "xxx",
"username": "xxx"
}'
```

## 执行多条shell命令

| 分隔符 | 命令示例       | 执行逻辑                                             |
| ------ | -------------- | ---------------------------------------------------- |
| ;      | cmd1 ; cmd2    | 不管前面的命令是否执行成功，都会执行后面的命令。     |
| &&     | cmd1 && cmd2   | 只有在前面的命令执行成功后，才会接着执行后面的命令。 |
| \|\|   | cmd1 \|\| cmd2 | 只有在前面的命令执行失败后，才会接着执行后面的命令。 |

## 磁盘分区与挂载

```
查看磁盘情况
lsblk
fdisk -l
df -h
特定分区创建文件系统
mkfs -t ext4 /dev/sdb
挂载分区
mount /dev/sdb /data
解除挂载
umount /dev/sdb
设置开机启动命令
vi /etc/rc.d/rc.local
chmod 755 /etc/rc.d/rc.local

/etc/fstab
```

## 查看cpu信息-lscpu

```
lscpu
```

## 查看内存情况-free

```
free
```

## 实时系统监控工具-top

```
top
```

## 修改用户账号

```
passwd <username>
```

## 解锁用户密码锁定

```
sudo pam_tally2 --reset --user <username>
```

## 目录树生成工具-tree

```
tree -L 2
```

## 查看系统时间

```
date
date -R
hwclock
```

## 包管理工具

### apk

alpine

```
apk -I list 列出已安装
```

### yum

centos



### rpm

rhel、centos



### apt

ubuntu、debian

```
# 更新所有已安装的软件包
$ apt-get upgrade
# 更新
$ apt-get update
# 安装一个新软件包
$ apt-get install packagename
# 卸载一个已安装的软件包（保留配置文件）
$ apt-get remove packagename
# 卸载一个已安装的软件包（删除配置文件）
$ apt-get –purge remove packagename
# 列出已安装软件包
$ sudo apt list --installed
```



### dpkg

debian系

```
# 列出当前已安装的包
dpkg-query -l
# 安装包
$ dpkg -i package.deb
# 删除包
$ dpkg -r package
# 删除包（包括配置文件）
$ dpkg -P package
# 显示该包的版本
$ dpkg -l package
# 搜索所属的包内容
$ dpkg -S keyword
```

## 软连接

```
# 读取链接地址
readlink -f ds-example.conf
```

## 防火墙

查看防火墙状态

```
systemctl status firewalld
```

启动防火墙

```
systemctl start firewalld
```

重启防火墙

```
service firewalld restart
systemctl restart firewalld
```

关闭防火墙

```
systemctl stop firewalld
```

开机禁用

```
systemctl disable firewalld
```

开机启动

```
systemctl enable firewalld
```

重新加载配置

```
firewall-cmd --reload
```

查看已开放端口

```
firewall-cmd --list-ports
```

查看所有策略

```
firewall-cmd --list-all
```

开放端口

```
开放指定端口
firewall-cmd --zone=public --permanent --add-port=9080/tcp --add-port=8090/tcp

批量开放80到90之间的所有端口
firewall-cmd --zone=public --add-port=80-90/tcp --permanent

允许单个ip访问80端口
firewall-cmd --permanent --remove-rich-rule="rule family='ipv4' source address='192.168.0.200' port protocol='tcp' port='80' accept"

批量允许多ip访问80端口
firewall-cmd --permanent --remove-rich-rule="rule family='ipv4' source address='192.168.3.0/24' port protocol='tcp' port='80' accept"
```

通过修改文件来控制

```
/etc/firewalld/zones/public.xml

<?xml version="1.0" encoding="utf-8"?>
<zone>
  <short>Public</short>
  <description>For use in public areas. You do not trust the other computers on networks to not harm your computer. Only selected incoming connections are accepted.</description>
  <service name="ssh"/>
  <service name="dhcpv6-client"/>
  <port protocol="tcp" port="10022"/>
  <rule family="ipv4">
    <source address="10.78.2.81"/>
    <port protocol="tcp" port="9000"/>
    <accept/>
  </rule>
  <rule family="ipv4">
    <source address="10.78.2.82"/>
    <port protocol="tcp" port="9000"/>
    <accept/>
  </rule>
</zone>
```

