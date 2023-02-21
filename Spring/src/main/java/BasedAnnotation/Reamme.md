#### 注解
    元注解：注解在注解上的注解
    组合注解：被注解的注解
    组合注解具备元注解的功能
#### @Enable*注解
    通过@Enable*来开启对某一项功能的支持，从而避免自己配置大量的代码
    @Enable*中都使用了@Import来导入配置类
    
    ①直接导入配置类    例如：@EnableScheduling
    ②条件导入配置类    例如：@EnableAsync
    ③动态注册Bean      例如：@EnableAspectJAutoProxy
#### Bean的初始化和销毁
    Java配置方式：使用@Bean的initMethod和destroyMethod(相当于xml配置的init-method和destory-method)
    注解方式：利用JSR-250的@PostConstruct(构造函数执行完之后执行，等同initMethod)和@PreDestroy(Bean销毁之前执行，等同destroyMethod)

##### @Bean
    大部分开发中都需要引入第三方jar包，往往并没有这些包的源码，这时不能使用@Component注解。
    这种情况下Spring提供了@Bean注解，它可以注解在方法上，并将方法返回的对象作为Spring的Bean注入

##### @Import注解是引入带有@Configuration的java类。

##### @ImportResource是引入spring配置文件.xml
