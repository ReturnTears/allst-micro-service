# 微服务项目搭建
新建空目录：allst-micro-service

## 🍌 其他模块
模块名： allst-micro-bom
```text
该模块主要用于项目的依赖管理，因此该模块的打包方式为pom
添加SpringBoot、SpringCloud相关依赖

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

## 🥥 广告模块
模块名： allst-micro-ad

开发广告业务：
+ 1、新建两个子模块api 和 impl
+ 2、新建子模块是借鉴了Dubbo的设计思路

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

3、
```

## Code Review
```text
项目的Code Review记录都记录在JetBrains Space 中
```