package AOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName AOPConfig
 * @Description
 * @Date 2019/10/12
 * @Created by lizhanxu
 */
@ComponentScan("AOP")
@EnableAspectJAutoProxy//想要成功初始化Context需要引入aspectjweaver依赖，提供织入支持
public class AOPConfig {
}
