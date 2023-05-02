# fun-stuff-jboss-logging-tool

1. 通过`Message Bundle Interfaces` 提供国际化异常和文本；
2. 通过`Message Logger Interfaces` 提供国际化日志；

参考文档：

- [jboss-logging-tool](https://jboss-logging.github.io/jboss-logging-tools/)
- [jboss-logging]https://github.com/jboss-logging

## jboss-logging bridge to slf4j

```xml
<dependency>
    <groupId>org.jboss.slf4j</groupId>
    <artifactId>slf4j-jboss-logmanager</artifactId>
    <version>2.0.1.Final</version>
</dependency>
```
