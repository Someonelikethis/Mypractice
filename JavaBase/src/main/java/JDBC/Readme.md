### 存储过程
    一组为了完成特定功能的SQL语句集(包括增删改查、条件语句、循环语句等)
    
    语法(MySQL)：
        DELIMITER //
        CREATE PROCEDURE 存储过程名称(IN|OUT|INOUT 参数名 参数类型)
        BEGIN
          一组SQL语句、控制语句、变量声明和设置语句
        END //
        
    创建示例：
        DELIMITER //
        CREATE PROCEDURE update_pro(IN un VARCHAR(20),IN ps VARCHAR(30))
        BEGIN
        UPDATE jdbctest SET `password`=ps WHERE username = un;
        END //
        
    调用示例：
        CALL update_pro('root','123456')
    
    优点：
        可重复使用；把存储过长看作一个函数，对一组SQL的封装。
        增强了SQL语句灵活性。存储过程可以使用控制语句，完成复杂的判断和较复杂的运算。
        减少网络流量，降低网络负载。一般的做法是每次都将大量的SQL语句通过网络发送到数据库服务器端执行，而存储过程存储在数据库中，只需要调用存储过程即可。
        较快的执行速度。存储过程是预编译的，只在创建时编译，查询优化器对其进行分析、优化，并给出最终被存在系统表中的存储计划，一次编译永久有效，提高数据库执行速度，一般SQL语句每次执行都要编译
        安全。通过设定某一存储过程的权限对相应的数据访问进行限制，确保数据安全。
        松耦合。存储过程大大地减少了业务系统与数据库的交互，一定程度降低了业务系统与数据库的耦合；存储过程能作为应用和DB之间的接口，以达到松耦合。
        使用场景：
                存储过程处理比较稳定、复杂的业务时比较实用。 比如说，一个复杂的数据操作。如果你在前台处理的话。可能会涉及到多次数据库连接。但如果你用存储过程的话。
                就只有一次。从响应时间上来说有优势。 也就是说存储过程可以给我们带来运行效率提高的好处。 
    缺点：
        需要多花时间调试。
        在不同数据库之间迁移，需要重写所有存储过程。存储过程往往定制化于特定数据库上，不同数据库对存储过程的支持和编程语法不同。比如MongDB不支持存储过程，MySQL存储过程很辣鸡
        数据库结构变更(比如增加字段，修改表结构)，需要修改所有相关的存储过程，这个是神坑
        商业逻辑要求聚合多种格式、多个来源的数据，并且要进行数据适配，这种工作适合放在中间层而不是数据库里实现
        数据库集群，实现各数据库Server端存储过程代码变更同步，很麻烦

### 事务
    由有限的数据库操作序列组成的逻辑执行单元，这系列操作要么全部执行，要么全部放弃执行。
    ACID——原子性(Atomicity)、一致性(Consistenecy)、隔离性(Isolation)、持久性(Durability)
    数据库事务中，包含一组DML语句，DDL和DCL语句最多只能有一条，因为DDL和DCL语句会导致事务立即提交。
    事务提交的两种方式：
        显示提交：使用commit
        自动提交：执行DDL或DCL语句，或者程序正常退出
    当事务所包含的任意一个数据库操作执行失败后，应该回滚事务，即放弃所有修改
    事务回滚的两种方式：
        显示回滚：使用rollback
        自动提交：系统错误或者强行退出
    
    MySQL默认关闭事务(即打开自动提交)
    
    设置事务中间点：
        savepoint a;
        rollback to a;
    普通的提交、回滚都会结束当前事务，但回滚到指定中间点仍处于事务之中
    


### SQL语句分类：
    DML(Data Manipulation Language,数据操作语言) insert、update、delete
    DDL(Data Definition Language,数据定义语言) create、alter、drop、truncate(截断)
    DCL(Data Control Language,数据控制语言) grant、revoke
    事务控制语句：commit、rollback、savepoint

    SQL语句的关键字不区分大小写

### truncate关键字——截断某个表
    删除该表里的全部数据，但保留表结构，比delete更快
    使用：truncate 表名

### JDBC常用接口和类
    DriverManager   用于管理JDBC驱动的服务类，主要用于获取Connection对象
    Connection      代表数据库连接对象，每个Connection代表一个物理连接会话
    Statement(不允许使用占位符?)       用于执行SQL语句的工具接口
    
    PreparedStatement(使用占位符?,带参数SQL)  
        预编译的Statement对象，是Statement的子接口，它允许数据库预编译SQL语句(这些SQL语句通常带有参数)，
        以后每次只改变SQL命令的参数，避免每次都需要编译SQL语句，性能更好。
        可以防止SQL注入。
        占位符不能用来代替表名、列名等数据库对象，或者SQL关键字，只能代替普通值
        
    CallableStatement 调用存储过程
    
    ResultSet       结果集对象，在逻辑结构上类似于一个表

### JDBC编程步骤：
    ①加载数据库驱动
    ②通过DriverManager获取数据库连接
    ③通过Connection对象创建Statement对象(createStatement(),prepareStatement(),prepareCall())
    ④使用Statement执行SQL语句，得到结果集
    ⑤操作结果集
    ⑥回收数据库资源(所有用到的资源，包括Connection、Statement、ResultSet)

### 正确关闭数据库资源的姿势：
    ①手动逐一关闭所有资源
    ②利用try(...)的自动关闭
### 资源池
    资源池是对于共享资源的情况的一种通用设计模式，用于解决资源的频繁请求、释放所造成的性能下降
### 数据库连接池
    解决方案：当应用程序启动时，系统主动建立足够多的的数据库连接，组成一个连接池。每当应用程序请求数据库连接时，从连接池中取出已有的连接，用完后归还给连接池
    数据库连接池是Connection对象的工厂
    常用参数：初始连接数、最大连接数、最小连接数、连接池每次增加的容量
### 数据源(DataSource)
    包含数据库连接池和连接池管理两部分
    习惯上常把DataSource称为连接池
### 两种开源的数据源实现——DBCP和C3P0
    DBCP：Tomcat的连接池
    C3PO：Hibernate推荐该连接池
    C3PO连接池比DBCP性能更好，自动清理不再使用的Connection、Statement、ResultSet