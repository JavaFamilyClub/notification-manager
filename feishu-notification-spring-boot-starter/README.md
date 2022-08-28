# 飞书通知处理器

> 基于 WebHook 的飞书机器人通知

## 1. 引入依赖

* Maven Central Release (Maven 中央仓库正式版)

``` xml
<dependency>
   <groupId>club.javafamily</groupId>
   <artifactId>feishu-notification-spring-boot-starter</artifactId>
   <version>2.3.2-beta.8</version>
</dependency>
```

* Maven Central Snapshot (Maven SNAPSHOT 仓库新功能尝鲜)

``` xml
   <!-- Snapshot 库需确保 snapshots 是被允许的 -->
   <repositories>
      <repository>
         <id>maven-central</id>
         <url>https://oss.sonatype.org/content/repositories/snapshots</url>
         <releases>
            <enabled>false</enabled>
            <updatePolicy>never</updatePolicy>
         </releases>
         <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
         </snapshots>
      </repository>
   </repositories>

   <dependencies>
      <dependency>
         <groupId>club.javafamily</groupId>
         <artifactId>feishu-notification-spring-boot-starter</artifactId>
         <version>2.3.2-beta.10</version>
      </dependency>
   </dependencies>
```

## 2. 配置

### 2.1 飞书通知配置

> 创建你自己的飞书 WebHook 机器人, 在 application.yml 中配置飞书通知的 webhook 地址

```yml
javafamily:
   notify:
      feishu:
         hook-url: https://open.feishu.cn/open-apis/bot/v2/hook/09973b31-0c1a-4924-b900-6173bb429644
         enabled: true  # 是否开启通知, 用于不同环境下的区分(开发, 测试, 生产), 默认为 true
```

### 2.2 抑制策略

> 当我们需要对通知进行抑制时(如: 通过飞书通知一些接口异常、服务宕机等信息, 有时候并不需要一直推送通知消息), 此时, 就可以通过抑制策略进行通知消息的抑制!

```yml
javafamily:
   notify:
      feishu:
         hook-url: https://open.feishu.cn/open-apis/bot/v2/hook/31a65e6b-0dab-491c-8de9-df3d16c19050
         inhibit:
            enabled: on # 默认为 off
            ttl: 1h # 代表同一个消息, 1h 只推送一次
```

> 通过指定 `inhibit` 属性进行抑制配置, 目前支持的属性有:
> * `enabled`: 是否开启抑制
> * `ttl`: 抑制时效(同样的通知多久发送一次)

> 通知抑制是通过 [javafamily-cache 组件](https://github.com/JavaFamilyClub/javafamily-cache) 提供组件服务与配置, 因此,
> `feishu-notification-spring-boot-starter` 同样支持 `JavaFamilyClub/javafamily-cache` 组件的全部配置.
> 如:

```yml
javafamily:
  notify:
    feishu:
      hook-url: https://open.feishu.cn/open-apis/bot/v2/hook/31a65e6b-0dab-491c-8de9-df3d16c19050
      inhibit:
        enabled: on
        ttl: 3s

  cache:
    type: caffeine # redis
    key-prefix: demo- # 缓存 key 前缀
    time-to-live: 20s # 缓存 expire 时间
    caffeine: # caffeine 缓存相关配置
      max-size: 500
      weak-keys: on
      soft-values: on
      record-stats: on
```

> 需要注意, `cache.time-to-live` 与 `inhibit.ttl` 如果都配置, 则 `inhibit.ttl` 优先级更高(生效).

> 更多配置请查看 [JavaFamilyClub/javafamily-cache](https://github.com/JavaFamilyClub/javafamily-cache)

### 2.3 restTemplate 配置

> 发送 webhook 请求底层是通过封装的 `resttemplate` 进行请求,
> 而 `restTemplate` 是通过 [javafamily-resttemplate-starter](https://github.com/JavaFamilyClub/javafamily-core/tree/main/javafamily-resttemplate-starter)
> 提供组件服务与配置, 因此, `feishu-notification-spring-boot-starter` 天生支持 `javafamily-resttemplate-starter` 组件的全部配置.
>
> 如: 配置代理(支持 http 及 socks 代理)

``` yml
javafamily:
   notify:
      feishu:
         hook-url: http://open.feishu.cn/open-apis/bot/v2/hook/09973b31-0c1a-4924-b900-6173bb429644

   http:
      proxy:
         type: http # type: socks
         host: 192.168.56.27
         port: 10080
```

> 更多 `restTemplate` 的配置请参考: [javafamily-resttemplate-starter](https://github.com/JavaFamilyClub/javafamily-core/tree/main/javafamily-resttemplate-starter)

## 3. 注入 `FeiShuNotifyHandler`

``` java
@SpringBootTest
public class FeiShuNotifyTests {

   @Autowired
   private FeiShuNotifyHandler feiShuNotifyHandler;
```

## 4. 创建 Request, 发送通知

* Text 通知

```java
   @Test
   void testNotifyText() {
      final String response = feiShuNotifyHandler.notify(
         FeiShuTextNotifyRequest.of("这是一个测试数据!"));

      log.info(response);
   }
```

![image-20220806170743367](img/README//image-20220806170743367.png)

* Post 通知

```java
   @Test
   void testNotifyPost() {
      final FeiShuPostNotifyRequest request = FeiShuPostNotifyRequest.of(
         "项目更新通知(测试)",
         new BaseTextTagContentItem("(测试)项目有更新: "),
         new LinkTagContentItem("请查看",
            "https://github.com/orgs/JavaFamilyClub/projects/3"));

      final String response = feiShuNotifyHandler.notify(request);

      log.info(response);
   }
```

![image-20220806170844395](img/README//image-20220806170844395.png)

* Card 通知

``` java
   @Test
   void testNotifyCard() {
      String dataTime = "2022-06-05 23:00:00";
      int shouldCount = 20, actualCount = 20;
      String status = actualCount < shouldCount ? "异常" : "正常";

      String content = "数据时次: " + dataTime
         + "\n应收收据个数: " + shouldCount
         + "\n实收数据个数: " + actualCount
         + "\n监控状态: **" + status + "**";

      final FeiShuCardNotifyRequest request
         = FeiShuCardNotifyRequest.of("测试xxx数据监控", content,
         "立即前往系统查看 :玫瑰:️ ✅ \uD83D\uDDA5️",
         "https://github.com/orgs/JavaFamilyClub/projects/3");

      final String response = feiShuNotifyHandler.notify(request);

      log.info(response);
   }
```

![image-20220806170925022](img/README//image-20220806170925022.png)

## 5. 示例代码

> 所有的示例代码都在 [examples](./examples)

* `组件使用示例`: [组件使用示例项目](./examples/demo-notification-manager)
* `抑制通知示例`: [抑制配置示例项目](./examples/demo-notification-manager-inhibit)
