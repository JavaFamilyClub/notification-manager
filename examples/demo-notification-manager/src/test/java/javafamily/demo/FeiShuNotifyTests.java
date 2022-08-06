package javafamily.demo;

import club.javafamily.nf.request.*;
import club.javafamily.nf.request.tags.LinkTagContentItem;
import club.javafamily.nf.request.tags.BaseTextTagContentItem;
import club.javafamily.nf.service.FeiShuNotifyHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:03
 * @description
 */
@Slf4j
@SpringBootTest
public class FeiShuNotifyTests {

   @Autowired
   private FeiShuNotifyHandler feiShuNotifyHandler;

   @Test
   void contextLoad() {
      Assertions.assertNotNull(feiShuNotifyHandler);
   }

   @Test
   void testNotifyText() {
      final String response = feiShuNotifyHandler.notify(
         FeiShuTextNotifyRequest.of("这是一个测试数据!"));

      log.info(response);
   }

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

   @Test
   @Disabled("no valid image key for now")
   void testNotifyImage() {
      final FeiShuImageNotifyRequest request
         = FeiShuImageNotifyRequest.of("img_ecffc3b9-8f14-400f-a014-05eca1a4310g");

      final String response = feiShuNotifyHandler.notify(request);

      log.info(response);
   }

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


}
