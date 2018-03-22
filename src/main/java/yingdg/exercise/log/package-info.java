/**
 * Created by yingdg on 2017/11/6 0006.
 */
package yingdg.exercise.log;

/*
日志级别：all<trace<debug<info<warn<error<fatal<off
输出日志的格式
    %d{yyyy-MM-dd HH:mm:ss, SSS} : 日志生产时间
    %p : 日志输出格式
    %c : logger的名称
    %m : 日志内容，即 logger.info("message")
    %n : 换行符
    %C : Java类名
    %L : 日志输出所在行数
    %M : 日志输出所在方法名
    hostName : 本地机器名
    hostAddress : 本地ip地址

使用slf4j配合各log4j，log4j2，logback

slf4j引入：slf4j-api

commons-logging：jcl-over-slf4j
log4j：log4j，slf4j-log4j12
log4j2：log4j-core，log4j-api
logback：logback-classic，logback-core
spring：logback-ext-spring

其他组件：
slf4j和log4j*包是日志门面接口和实际日志发送者
log4j-slf4j-impl是用于log4j2桥接slf4j，便于使用slf4j声明，log4j2输出日志
log4j-nosql是为了使得log4j2支持非关系型数据库
log4j-web是为了在代码中使用Spring管理log4j2,通过Spring进行初始化，并应用Druid的数据源
 */