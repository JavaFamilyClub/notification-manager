package javafamily.demo;

import club.javafamily.nf.request.*;
import club.javafamily.nf.request.tags.LinkTagContentItem;
import club.javafamily.nf.request.tags.BaseTextTagContentItem;
import club.javafamily.nf.service.QyWechatNotifyHandler;
import club.javafamily.utils.common.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StreamUtils;
import sun.security.provider.MD5;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:03
 * @description
 */
@Slf4j
@SpringBootTest
public class QyWechatNotifyTests {

   @Autowired
   private QyWechatNotifyHandler qywechatNotifyHandler;

   @Test
   void contextLoad() {
      Assertions.assertNotNull(qywechatNotifyHandler);
   }

   @Test
   void testNotifyText() {
      final String response = qywechatNotifyHandler.notify(
         QyWechatTextNotifyRequest.of("这是一个测试数据!"));

      log.info(response);
   }

   @Test
   void testNotifyMarkDown() {
      String dataTime = "2022-06-05 23:00:00";
      int shouldCount = 20, actualCount = 20;
      String status = actualCount < shouldCount ? "异常" : "正常";

      String content = "@18829346477 数据时次: " + dataTime
              + "\n应收收据个数: " + shouldCount
              + "\n实收数据个数: " + actualCount
              + "\n监控状态: **" + status + "**";

      QyWechatMarkdownNotifyRequest request = QyWechatMarkdownNotifyRequest.of(content);

      final String response = qywechatNotifyHandler.notify(request);

      log.info(response);
   }

//      final FeiShuPostNotifyRequest request = FeiShuPostNotifyRequest.of(
//              "项目更新通知(测试)",
//              new BaseTextTagContentItem("(测试)项目有更新: "),
//              new LinkTagContentItem("请查看",
//                      "https://github.com/orgs/JavaFamilyClub/projects/3"));

   @Test
   void testNotifyImage() throws Exception {
      String imagePath = "C:\\Temp\\test.jpg";

      Path path = Paths.get(imagePath);

      byte[] byteArray = Files.readAllBytes(path);
      QyWechatImageNotifyRequest request = QyWechatImageNotifyRequest.of(byteArray);

      final String response = qywechatNotifyHandler.notify(request);

      log.info(response);
   }

   @Test
   void testNotifyNews() throws Exception {
      QyWechatNewsNotifyRequest request = QyWechatNewsNotifyRequest.of(
              "金秋九月", "公司季度团建活动正式开始",
              "https://github.com/JavaFamilyClub/notification-manager",
              "https://t7.baidu.com/it/u=2204205512,3039153138&fm=193&f=GIF");

      final String response = qywechatNotifyHandler.notify(request);

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
         "https://github.com/orgs/JavaFamilyClub/projects/3");

//      final String response = qywechatNotifyHandler.notify(request);
//
//      log.info(response);
   }


}
