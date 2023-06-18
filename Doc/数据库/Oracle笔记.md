```sh
// 查看系统用户
more /etc/passwd
// 登录安装oracle的linux用户，登录root再登录oracle用户
su - oracle
// 登录dba
sqlplus / as sysdba
// 登录数据库用户
sqlplus cw/cw19730207
// 导出用户全部数据
exp user/password  file=xxx.dmp full=y
// 停用oracle
shutdown immediate
// 启用oracle
startup
```



授权

```
// 授权用户dba权限
grant dba to cw;
// 授权某表查询权限给某用户
grant select on fschat.sys_user to qyyp;
// 授权取别名
grant create synonym to qyyp;
// 解除用户表空间限额
grant unlimited tablespace to fschat;
```



取别名

```
create synonym sys_user from fschat.sys_user;
```



查询用户表空间名称

```
select default_tablespace from dba_users where username='登录用户';
```



修改用户密码

```
// 登录dba
sqlplus / as sysdba;
alter user 用户名 identified by 新密码;
```





