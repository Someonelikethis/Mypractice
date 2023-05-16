# Windows常用命令

## ipconfig

```
查看本机网络，可以看到ip、网关、dns服务器等
ipconfig /all
```

## 查看占用端口对应的PID

```
netstat -ano|findstr "8081"
```

## 查看指定PID的进程

```
tasklist|findstr "9088"
```

