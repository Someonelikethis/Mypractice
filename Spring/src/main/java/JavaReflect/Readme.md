https://blog.csdn.net/sinat_38259539/article/details/71799078

# 获得Class对象的三种方法

①Object的getClass方法

②任何数据类型都有一个静态的class属性

③通过Class类的静态方法：forName（String className），
  其中className为类的全限定名
  
一般常用第三种

第一种对象都有了还反射干啥
第二种需要导入类的包，依赖太强，不导包就抛编译错误

# 反射main方法
通过反射main方法，可以在一个类的main方法中使用另一个类的main方法


