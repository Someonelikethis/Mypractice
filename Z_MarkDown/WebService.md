# WebService

## 简介

> 一种跨编程语言和跨操作系统平台的远程调用技术。
>
> WebService所使用的是Internet上统一、开放的标准，如HTTP、XML、SOAP（简单对象访问协议）、WSDL等，所以Web Service可以在任何支持这些标准的环境（Windows,Linux）中使用。
>
> 有助于大量异构程序和平台之间的互操作性。
>
> 遵循WebService规范都可以相互交换数据或集成。
>
> JAVA 中共有三种WebService 规范，分别是JAX-WS（JAX-RPC）、JAXM&SAAJ、JAX-RS。
>
> WebService三要素：soap、wsdl、uddi。
>
> **WebService交互的过程就是,WebService遵循SOAP协议通过XML封装数据，然后由Http协议来传输数据。**

## WebService三要素

### SOAP协议

> Simple Object Access Protocal
>
> 简单对象访问协议
>
> 用于交换XML（标准通用标记语言下的一个子集）编码信息的轻量级协议
>
> SOAP = 在HTTP的基础上 + XML数据

SOAP的组成：

* Envelope - 必须的部分。以XML的根元素出现
* Headers - 可选的。
* Body - 必须的。在body部分，包含要执行的服务器的方法和发送到服务器的数据

### WSDL说明书

> WebService Definition Language
>
> WebService描述语言
>
> 就是一个XML文件
>
> 用来描述WebService，以及如何访问WebService
>
> WSDL既是机器可阅读的，又是人可阅读的

* 1） 通过wsdl说明书，就可以描述webservice服务端对外发布的服务；
* 2） wsdl说明书是一个基于xml文件，通过xml语言描述整个服务；
* 3） 在wsdl说明中，描述了：
* 对外发布的服务名称（类）
  		* 接口方法名称（方法）
    * 接口参数（方法参数）
    * 服务返回的数据类型（方法返回值）

### UDDI

> Universal Description,Discovery and Intergration
>
> 帮助Web服务提供商在互联网上发布Web服务信息
>
> UDDI是一种目录服务，企业可以通过UDDI来注册和搜索Web服务

## WebService开发规范

> JAVA 中共有三种WebService 规范，分别是JAX-WS（JAX-RPC）、JAXM&SAAJ、JAX-RS。

### JAX-WS

> Java API For XML-WebService
>
> 基于soap协议

### JAXM&SAAJ

> Java API For XML Message，主要定义了包含发送和接收消息所需的API
>
> SOAP With Attachment API For Java，与JAXM搭配使用的API
>
> 暴露出soap协议更多的细节，使用较少

### JAX-RS

> 针对RESTFUL定制的一套规范

## 面向服务的架构SOA

> 将应用程序的不同功能单元通过中立的契约联系起来，方便集成
>
> WebService是SOA的一种较好的实现

## ApacheCXF框架

> 提供了对JAX-WS的全面支持