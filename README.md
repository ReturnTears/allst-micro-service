# 微服务项目搭建
新建空目录：allst-micro-service

## 🍌 其他模块
模块名： allst-micro-bom
```text
该模块主要用于项目的依赖管理，因此该模块的打包方式为pom
添加SpringBoot、SpringCloud相关依赖
其他模块会依赖该模块，需将当前模块install到本地仓库中
```
## 🍓 公共模块
模块名： allst-micro-common
```text
该模块主要用于存放项目的工具类
1、属性拷贝工具类：ConverUtil
2、

将当前模块安装到本地仓库：maven install, 方便后续其他模块引用
```

## 🍎 前台搭建
模块名： allst-micro-front

## 🍋 后台搭建
模块名： allst-micro-boot

```text
http://localhost:8082/ad/space/getAllSpaces
boot模块中通过远程调用访问api模块中service接口，api会访问impl模块的实现，不通过impl模块的前端控制器即可访问后端服务
```

## 🥥 广告模块
模块名： allst-micro-ad

开发广告业务：
+ 1、新建两个子模块allst-micro-ad-api 和 allst-micro-ad-impl, 以下简称： api 和 impl
+ 2、新建子模块是借鉴了Dubbo的设计思路

```text
api模块提供对外调用的服务
impl提供业务实现相关的服务

api 使用openfeign编写好远程调用服务后，将api模块安装到本地仓库中
impl 引用 api模块
```

## 🍑 注册中心
模块名： allst-micro-eureka

配置注册中心流程：
+ 1、添加application.yml配置文件，并添加配置
+ 2、添加Eureka Server启动类
+ 3、添加项目启动类，启动项目成功后访问：http://localhost:8761


## 🍉 配置中心
模块名：allst-micro-config

配置中心流程（以获取GitHub上配置文件为例）：
- 1、在allst-config（该想要需要为公共访问权限，私有访问权限放在后续介绍）项目中新建配置文件config-mirco-service-dev.yml
- 2、添加application.yml配置文件，并添加配置
- 3、添加项目启动类，启动成功后访问：http://localhost:8090/config-micro-service-dev.yml
- 4、访问配置文件成功，文件存放在：file:/C:/Users/June/AppData/Local/Temp/config-repo-8257864722419767941/config-micro-service-dev.yml

## 🍒 网关服务
模块名：allst-micro-gateway


## 问题记录
```text
1、启动SpringCloud项目启动报错：Error creating bean with name peerEurekaNodes defined in class path resource
解决思路：新版本的Spring Cloud对熔断器Hystrix有要求，Eureka中必须添加对Hystrix的依赖才可以。
添加依赖：
    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
     </dependency>

2、编译或者启动项目报错：Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project 项目名称或模块名称: Fatal error compiling
解决思路：pom.xml中添加配置
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

3、 pom中parent标签：<relativePath/>
设定一个空值将始终从中央仓库中获取，不从本地路径获取，如<relativePath />  

4、运行项目时报错：org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal on project xxx
项目结构说明：项目结构是一个父项目，多个子项目目录如下，一个子项目依赖于另一个子项目，这时候运行项目报上述错误，解决如下：
    先对父项目进行clean和install，再运行项目
```

## Code Review
```text
项目的Code Review记录都记录在JetBrains Space 中
```