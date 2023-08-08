package javafamily.demo;

import club.javafamily.nf.request.*;
import club.javafamily.nf.request.card.*;
import club.javafamily.nf.request.content.TitleDescContent;
import club.javafamily.nf.service.QyWechatNotifyHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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
   void testTextNotifyCard() {
      QyWechatCardRequestContent requestContent = new QyWechatCardRequestContent();

      requestContent.setSource(QyWechatCardRequestContentSource.builder()
              .icon_url("https://t7.baidu.com/it/u=2204205512,3039153138&fm=193&f=GIF")
              .desc("JavaFamily 移动办公")
              .desc_color(3)
              .build());

      requestContent.setMain_title(TitleDescContent.of("周任务完成率", "周任务平均完成率最终稿通告"));
      requestContent.setEmphasis_content(TitleDescContent.of("85%", "本月任务平均完成率"));

      requestContent.setQuote_area(QyWechatCardRequestContentQuoteArea.builder()
              .type(0)
              .title("Top1")
              .quote_text("JackLi: 100%")
              .build());

      requestContent.setSub_title_text("查阅详情");
      requestContent.setHorizontal_content_list(Arrays.asList(
              QyWechatCardRequestContentHorizontal.builder()
                      .type(1)
                      .keyname("在线文档")
                      .value("周任务完成率在线文档")
                      .url("https://t7.baidu.com/it/u=2204205512,3039153138&fm=193&f=GIF")
                      .build(),
              QyWechatCardRequestContentHorizontal.builder()
                      .type(2)
                      .keyname("文件下载")
                      .value("周任务完成率")
                      .media_id("3JsHtiugcvnuMVGIvjGMT4I5TihgRFZZRazoD5CR52P3ASvpf2IqpG6bsCV2NPi1m")
                      .build()));

      requestContent.setJump_list(Arrays.asList(QyWechatCardRequestContentJump.builder()
                      .type(1)
                      .title("错误反馈")
                      .url("https://github.com/JavaFamilyClub/notification-manager")
                      .build(),
              QyWechatCardRequestContentJump.builder()
                      .type(0)
                      .title("评判规则")
                      .build()));

      requestContent.setCard_action(QyWechatCardRequestContentCardAction.builder()
              .type(1)
              .url("https://github.com/JavaFamilyClub/notification-manager")
              .build());

      QyWechatCardNotifyRequest request = new QyWechatCardNotifyRequest(requestContent);

      String response = qywechatNotifyHandler.notify(request);

      log.info(response);
   }

   @Test
   void testTextNotifyCard2() {
      QyWechatCardNotifyRequest request = QyWechatCardNotifyRequest.of(
              "周任务完成率", "周任务平均完成率最终稿通告",
              "85%", "本月任务平均完成率",
              "Top1", "JackLi: 100%",
              "https://github.com/JavaFamilyClub/notification-manager");

      QyWechatCardRequestContent requestContent = new QyWechatCardRequestContent();
      requestContent.setSub_title_text("查阅详情");
      requestContent.setHorizontal_content_list(Arrays.asList(
              QyWechatCardRequestContentHorizontal.builder()
                      .type(1)
                      .keyname("在线文档")
                      .value("周任务完成率在线文档")
                      .url("https://t7.baidu.com/it/u=2204205512,3039153138&fm=193&f=GIF")
                      .build(),
              QyWechatCardRequestContentHorizontal.builder()
                      .type(2)
                      .keyname("文件下载")
                      .value("周任务完成率")
                      .media_id("3JsHtiugcvnuMVGIvjGMT4I5TihgRFZZRazoD5CR52P3ASvpf2IqpG6bsCV2NPi1m")
                      .build()));

      requestContent.setJump_list(Arrays.asList(QyWechatCardRequestContentJump.builder()
                      .type(1)
                      .title("错误反馈")
                      .url("https://github.com/JavaFamilyClub/notification-manager")
                      .build(),
              QyWechatCardRequestContentJump.builder()
                      .type(0)
                      .title("评判规则")
                      .build()));

      String response = qywechatNotifyHandler.notify(request);

      log.info(response);
   }

   @Test
   void testNotifyImageTextCard() {
      QyWechatCardRequestNewsContent requestContent = new QyWechatCardRequestNewsContent();

      requestContent.setSource(QyWechatCardRequestContentSource.builder()
              .icon_url("https://t7.baidu.com/it/u=2204205512,3039153138&fm=193&f=GIF")
              .desc("JavaFamily 移动办公")
              .desc_color(3)
              .build());

      requestContent.setMain_title(TitleDescContent.of("周任务完成率", "周任务平均完成率最终稿通告"));

      requestContent.setCard_image(QyWechatCardRequestContentCardImage.builder()
              .url("https://t7.baidu.com/it/u=2204205512,3039153138&fm=193&f=GIF")
              .aspect_ratio(2.25F)
              .build());

      requestContent.setImage_text_area(QyWechatCardRequestContentImageTextArea.builder()
              .type(1)
              .url("https://github.com/JavaFamilyClub/notification-manager")
              .image_url("https://hbimg.huabanimg.com/e51c5ec5a5b1e43128662531ea49732dbf1331991fe56-WQTUEo_fw658")
              .title("欢迎使用 JavaFamily notification-manager")
              .desc("JavaFamily notification-manager 是一个用于推送各类平台消息的 SDK")
              .build());

      requestContent.setQuote_area(QyWechatCardRequestContentQuoteArea.builder()
              .type(0)
              .title("Top1")
              .quote_text("JackLi: 100%")
              .build());

      requestContent.setVertical_content_list(Arrays.asList(
              TitleDescContent.of("周任务剩余数", "5"),
              TitleDescContent.of("月度计划剩余数", "15")));

      requestContent.setHorizontal_content_list(Arrays.asList(
              QyWechatCardRequestContentHorizontal.builder()
                      .type(1)
                      .keyname("在线文档")
                      .value("周任务完成率在线文档")
                      .url("https://t7.baidu.com/it/u=2204205512,3039153138&fm=193&f=GIF")
                      .build(),
              QyWechatCardRequestContentHorizontal.builder()
                      .type(2)
                      .keyname("文件下载")
                      .value("周任务完成率")
                      .media_id("3JsHtiugcvnuMVGIvjGMT4I5TihgRFZZRazoD5CR52P3ASvpf2IqpG6bsCV2NPi1m")
                      .build()));

      requestContent.setJump_list(Arrays.asList(QyWechatCardRequestContentJump.builder()
                      .type(1)
                      .title("错误反馈")
                      .url("https://github.com/JavaFamilyClub/notification-manager")
                      .build(),
              QyWechatCardRequestContentJump.builder()
                      .type(0)
                      .title("评判规则")
                      .build()));

      requestContent.setCard_action(QyWechatCardRequestContentCardAction.builder()
              .type(1)
              .url("https://github.com/JavaFamilyClub/notification-manager")
              .build());

      QyWechatCardNotifyRequest request = new QyWechatCardNotifyRequest(requestContent);

      String response = qywechatNotifyHandler.notify(request);

      log.info(response);
   }

}
