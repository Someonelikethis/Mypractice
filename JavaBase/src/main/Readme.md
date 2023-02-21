### javax的x是extension的意思，也就是扩展包

### 子类去实现父类已实现的接口

`class A implements Interface`<br>
`class B extends A`<br>
`class C extends A implements Interface.`<br>

B和C都可以向上转型为Interface<br>
B和C在不覆盖接口定义的方法时都会用父类的实现<br>
#### 在反射方法getInterfaces()中, A和C类都会有Interface, 但是B不会有Interface

for (Object n : list) { System.out.println(n); }

list.forEach(n -> System.out.println(n));//有类型推断

list.forEach(System.out::println);//方法引用

| | | |
|-------|-------|-------|
|类名::类方法|(a,b,...)->类名.类方法(a,b,...)|函数式接口中被实现方法的全部参数传给该类方法作为参数|
|特定对象::实例方法|(a,b,...)->特定对象.实例方法(a,b,...)|函数式接口中被实现方法的全部参数传给该实例方法作为参数|
|类名::实例方法|(a,b,...)->a.实例方法(b,...)|函数式接口中被实现方法的第一个参数作为调用者，后面的参数全部传给该实例方法作为参数|
|类名::new|(a,b,...)->new 类名(a,b,...)|函数式接口中被实现方法的全部参数传给该构造器作为参数|
