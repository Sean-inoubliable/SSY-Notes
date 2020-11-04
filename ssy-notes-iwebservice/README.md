## SpringBoot整合WebService - 客户端版 - com.iws.axis2client

> 使用http直接发送xml报文直接调用WebService。

> 结合代码来看 README.md

### 准备工作：先提供一个WebService

第一步：创建一个java项目(这里不是重点,创建普通java项目就行),并创建几个java文件

- 说明：
  
    User被创建用于辅助演示的;
    
    MyWebService是用来编写提供的WebService服务的;
    
    MyServer中是用来发布MyWebService的。

第二步：检查一下发布是否成功,运行MyService主函数,并访问 http://127.0.0.1:9527/webservice/test?wsdl
访问到，说明发布成功。

第三步：下载SoapUI工具（本人使用的是 __ReadyAPI 3.1.0__）,并根据 http://127.0.0.1:9527/webservice/test?wsdl 获得xml模板

### 使用Http调用WebService

第一步：创建一个项目,并在pom.xml中引入依赖。pom.xml除了基本的依赖外,还需要:

    <!-- 如果使用的是 springframework的RestTemplate发送http,那么需要引入此依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
     
    <!-- 
         使用apache的httpclient发送http,需要引入httpclient依赖;
         使用OMElement需要引入axis2-transport-http依赖;改以来本身带有httpclient依赖,所以
             我们不在需要单独引入httpclient依赖了
     -->
    <dependency>
        <groupId>org.apache.axis2</groupId>
        <artifactId>axis2-transport-http</artifactId>
        <version>1.7.8</version>
    </dependency>

第二步：使用HTTP调用WebService

- HttpToWebService, Main函数通过 HTTP调用WebService
- SoapXML, HTTP请求体封装
- HttpSendUtil, HTTP工具类
- HttpToWebService 中利用axis2的omElement，将response返回体xml转换为omElement，结果见代码，遍历即可

## SpringBoot整合WebService - 服务端版本 - com.iws.cxfservice
### MyCXFWebService
- 服务端接口
- 注意接口中的方法

### MyCXFWebServiceImpl
- 服务端实现
- 注意实现类中的处理

### CxfConfig
- CXF配置
- 注意类中的两个字符串

### CxfClient
- 测试客户端
- 注意CxfConfig中字符串与client连接的关系