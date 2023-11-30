# Nginx

## 常用命令

```
查看已开放端口
firewall-cmd --list-ports
firewall-cmd --reload

开放端口
firewall-cmd --zone=public --permanent --add-port=8080/tcp --add-port=23333/udp

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

## 常用配置

### ssl证书配置

#### pem crt key

> crt和.key分别代表证书和私钥文件,扩展名是按照文件用途来分的。而.pem是一种文件格式, pem文件是文本格式的,其他证书格式还有DER。 所以证书.crt和.key文件可以是PEM格式文件, 也可以是其他证书格式比如DER(二进制格式) 

#### 建议

* 用于证书的443端口和用于访问的80端口，它们两个的server_name都要将有www和没有www的两个域名都加上

  如：`server_name  www.lizhanxu.com lizhanxu.com`

* 转发最好直接写完整的url不要用变量，因为当变量不止有唯一值的时候会产生一些错误

  最好不要：`rewrite ^(.*) https://$server_name$1 permanent;`

  正确：`rewrite ^(.*) https://www.lizhanxu.com permanent;`

#### 配置

```
最外层->http下
server {
	listen 19002 ssl;
	server_name suban.cqxx.com;
	ssl_certificate /etc/ssl/suban.cqxx.com.cer;
	ssl_certificate_key /etc/ssl/suban.cqxx.com.pem;
	ssl_session_timeout 5m;
	ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
	ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE;
	ssl_prefer_server_ciphers on;
	charset UTF-8;
	client_max_body_size 1000M; // 单次请求的body最大大小
	
	location / {
		root /usr/local/www;
		index index.html index.htm;
	}
	location /uc-api {
		proxy_pass http://localhost:8080;
	}
}
```

### TCP代理配置

```
最外层
stream {
    upstream fs-minio {
        server 10.30.108.226:9000;
    }

    server {
        listen 9006;
        proxy_connect_timeout 800s;
		proxy_timeout 24h;
        proxy_pass fs-minio;
    }

    server {
        listen 18888;
        proxy_connect_timeout 800s;
		proxy_timeout 24h;
        proxy_pass 10.30.108.224:9080;
    }
}
```

### 静态目录代理

```
最外层->http->server下
location /static/ {
    alias /data/flyshare/public/;
    autoindex on;
}
```

### 负载配置

```

```

## 常用参数

### proxy_pass

- **如果proxy_pass末尾有斜杠/，proxy_pass不拼接location的路径**
- **如果proxy_pass末尾无斜杠/，proxy_pass会拼接location的路径**

#### proxy_paxy末尾有斜杠

```
location  /api/ {
    proxy_pass http://127.0.0.1:8000/;
}
```

请求地址：`http://localhost/api/test`
转发地址：`http://127.0.0.1:8000/test`

#### proxy_paxy末尾无斜杠

```
location  /api/ {
    proxy_pass http://127.0.0.1:8000;
}
```

请求地址：`http://localhost/api/test`
转发地址：`http://127.0.0.1:8000/api/test`

注：`/api/`和`/api`区别是影响路径的匹配规则，例如`/apiUC`可以被`/api`匹配到，不能被`/api/`匹配到