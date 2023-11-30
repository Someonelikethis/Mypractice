# SpringBoot配置加载

[官方说明 2.7.14](https://docs.spring.io/spring-boot/docs/2.7.14/reference/html/features.html#features.external-config)

[官方说明 2.1.11](https://docs.spring.io/spring-boot/docs/2.1.11.RELEASE/reference/html/boot-features-external-config.html)

## 优先级

### 配置优先级(由高到低覆盖)

1. 命令行参数（即以 开头的参数`--`，例如`--server.port=9000`）
2. Java 系统属性 ( `System.getProperties()`)。
3. 操作系统环境变量。
4. 配置文件（例如`application.properties`文件）。

### 配置文件优先级(由高到低覆盖)

1. `/config`当前目录的子目录
2. 当前目录
3. 类路径`/config`包
4. 类路径根

注意：

1. jar外配置文件优先于jar内
2. 同一位置同时有yaml和properties两个配置文件，properties文件优先

## nacos

### nacos优先级

1. 命令行参数
2. Java 系统属性 ( `System.getProperties()`)。
3. 操作系统环境变量。
4. nacos配置
5. 配置文件（例如`application.properties`文件）。

### nacos配置失效原因

```yaml
spring:
  redis:
    host: 127.0.0.1
    
spring:
  datasource:
    driverClassName: oracle.jdbc.OracleDriver
```

如上，单个yaml文件中这样写，会导致上面的spring:中的所有配置失效，不只是nacos，yaml文件这样写本身就有这个问题

## 获取配置

1. @Value

2. @ConfigurationProperties

3. Environment

   ```java
   @Autowired
   private Environment environment;
   
   @Value("${spring.redis.host}")
   String redisHost;
   
   public void test(){
       System.out.println(redisHost);
       System.out.println(environment.getProperty("spring.redis.host"));
   }
   
   public Map getEnvironment() {
       List<String> list = new ArrayList<>();
       StandardServletEnvironment standardServletEnvironment = (StandardServletEnvironment) environment;
       Map<String, Map<String, String>> map = new HashMap<>(8);
       Iterator<PropertySource<?>> iterator = standardServletEnvironment.getPropertySources().iterator();
       while (iterator.hasNext()) {
           PropertySource<?> source = iterator.next();
           Map<String, String> m = new HashMap<>(128);
           String name = source.getName();
           //去除系统配置和系统环境配置
           if (name.equals(StandardServletEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME) || name.equals(StandardServletEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME)) {
               continue;
           }
           Object o = source.getSource();
           if (o instanceof Map) {
               for (Map.Entry<String, Object> entry : ((Map<String, Object>) o).entrySet()) {
                   String key = entry.getKey();
                   m.put(key, standardServletEnvironment.getProperty(key));
               }
           }
           map.put(name, m);
       }
       return map;
   }
   ```

   