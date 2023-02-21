# Exception Tips

所有异常的根类——Throwable实现了Serializable接口,所以都要显示指定serialVersionUID

java的异常被分为两大类：Checked异常和Runtime异常

所有的RuntimeException及其子类的实例都属于Runtime异常

不是Runtime异常的异常则都是Checked异常

java程序必须显示处理Checked异常，如果没有则无法通过编译

### Checked异常的两种处理方式：

①用try...catch来捕获，在catch块中修复该异常

②方法声明时抛出异常

Runtime异常无需显示声明抛出，在需要的时候可以用try...catch捕获

Runtime可以不做显示处理，但是会隐式的逐级往上抛，如果中途没有try...catch捕获处理，则最终会抛到JVM,由JVM处理

### 什么时候需要自定义异常？

①java自带的异常满足不了业务的需求, 这个时候需要自定义异常来进行对业务异常的处理

②隐藏java原生的异常，变成可以理解的业务相关异常

