# 飞书通知器
> 基于 WebHook 的飞书机器人通知

## 1. 引入依赖

``` xml
<dependency>
   <groupId>club.javafamily</groupId>
   <artifactId>feishu-notification-spring-boot-starter</artifactId>
   <version>2.3.2-beta</version>
</dependency>
```

## 2. 配置

> 创建你自己的飞书 WebHook 机器人, 在 application.yml 中配置飞书通知的 webhook 地址

```yml
javafamily:
   notify:
      feishu:
         hook-url: https://open.feishu.cn/open-apis/bot/v2/hook/31a65e6b-0dab-491c-8de9-df3d16c19050
```

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
         "https://github.com/orgs/JavaFamilyClub/projects/3",
         null);

      final String response = feiShuNotifyHandler.notify(request);

      log.info(response);
   }
```

![image-20220806170925022](img/README//image-20220806170925022.png)
