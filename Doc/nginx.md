## Nginx

### 常用命令

```
查看已开放端口
firewall-cmd --list-ports

开放端口
firewall-cmd --zone=public --add-port=23333/udp --permanent

重启防火墙
systemctl reload firewalld

systemctl stop firewalld

systemctl start firewalld

systemctl status firewalld

ps -ef | grep nginx

netstat -tunlp|grep 80



./nginx 启动

./nginx -s stop 关闭

./nginx -s reload 重启
./nginx -t
```

配置阿里云安全组，即放开对应的端口，让其他主机可以访问

### 常用配置

```

```

### 建议

* 用于证书的443端口和用于访问的80端口，它们两个的server_name都要将有www和没有www的两个域名都加上

  如：`server_name  www.lizhanxu.com lizhanxu.com`

* 转发最好直接写完整的url不要用变量，因为当变量不止有唯一值的时候会产生一些错误

  最好不要：`rewrite ^(.*) https://$server_name$1 permanent;`

  正确：`rewrite ^(.*) https://www.lizhanxu.com permanent;`