# MySQL

## 常用命令

### 用户和授权

#### 创建用户

```
CREATE USER 'username'@'host' [IDENTIFIED BY 'password'];
username:要创建的用户名；
host:代表地址；任何地址可以使用%
IDENTIFIED BY 'password':设置密码，如果不写则为空密码

eg:
create user 'fschat'@'%' IDENTIFIED by 'Fschat123';
```

#### 删除用户

```
DROP USER 'username'@'host';
```

#### 设置密码

```
SET PASSWORD FOR 'username'@'host'=PASSWORD('your_password');
```

#### 更改密码

```
alter user 'username'@'host' identified by 'your_password';
```

#### 授权

```
GRANT privileges ON dbName.tableName TO 'username'@'host' [WITH GRANT OPTION];
privileges:用户的操作权限，如select,delete,update等，共14个，all代表所有。
dbname:数据库名，*代表所有
tablename:表名，*代表所有
WITH GRANT OPTION: 被授权的用户可以将他的拥有的权限授给其他用户

eg:
grant all on fschat.* to 'fschat'@'%';
grant all on *.* to 'fschat'@'%';
```

#### 刷新授权

```
flush privileges;  // 刷新授权，使之立即生效
```

#### 查看授权

```
show grants; // 查看当前用户（自己）权限
show grants for fschat@'%'; // 查看其他用户权限
```

#### 撤销授权

```
REVOKE privilege ON dbname.tablename FROM 'username'@'host';

eg:
revoke all on *.* to 'fschat'@'%';
```

## 问题解决记录

MySQL 连接出现 Authentication plugin 'caching_sha2_password' cannot be loaded

解决方法

show databases;

use mysql;

select host,user,plugin from user where user='root';

更改密码插件，将之前用caching_sha2_password的地方更改为mysql_native_password

ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';

'root'为caching_sha2_password的user，通过查询得出

'%'为caching_sha2_password的host，通过查询得出

'123456'为新密码