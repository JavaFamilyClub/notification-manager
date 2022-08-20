package javafamily.demo;

import club.javafamily.nf.enums.CardBtnOrientationEnum;
import club.javafamily.nf.request.FeiShuCardNotifyRequest;
import club.javafamily.nf.request.FeiShuImageNotifyRequest;
import club.javafamily.nf.request.FeiShuPostNotifyRequest;
import club.javafamily.nf.request.card.feed.DingTalkFeedCardRequest;
import club.javafamily.nf.request.card.feed.FeedCardRequestContentLink;
import club.javafamily.nf.request.card.multi.DingTalkMultiBtnCardRequest;
import club.javafamily.nf.request.card.single.DingTalkSingleBtnCardRequest;
import club.javafamily.nf.request.link.DingTalkLinkRequest;
import club.javafamily.nf.request.link.LinkRequestContent;
import club.javafamily.nf.request.markdown.DingTalkMarkDownRequest;
import club.javafamily.nf.request.tags.BaseTextTagContentItem;
import club.javafamily.nf.request.tags.CardBtn;
import club.javafamily.nf.request.tags.LinkTagContentItem;
import club.javafamily.nf.request.text.DingTalkTextNotifyRequest;
import club.javafamily.nf.service.DingTalkNotifyHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:03
 * @description
 */
@SpringBootTest
public class DingTalkNotifyTests {

   @Autowired
   private DingTalkNotifyHandler dingTalkNotifyHandler;

   @Test
   void contextLoad() {
      Assertions.assertNotNull(dingTalkNotifyHandler);
   }

   @Test
   void testNotifyText() {
      final String response = dingTalkNotifyHandler.notify(
         DingTalkTextNotifyRequest.of("这是一个测试数据!"));

      System.out.println(response);
   }

   @Test
   void testNotifyTextAt() {
      String dataTime = "2022-06-05 23:00:00";
      int shouldCount = 20, actualCount = 20;
      String status = actualCount < shouldCount ? "异常" : "正常";

      String content = "测试数据时次: " + dataTime
              + "\n应收收据个数: " + shouldCount
              + "\n实收数据个数: " + actualCount
              + "\n监控状态: **" + status + "**";

      final String response = dingTalkNotifyHandler.notify(
              DingTalkTextNotifyRequest.of(content, false, "18829346477"));

      System.out.println(response);
   }

   @Test
   void testNotifyLink() {
      String dataTime = "2022-06-05 23:00:00";
      int shouldCount = 20, actualCount = 20;
      String status = actualCount < shouldCount ? "异常" : "正常";

      String content = "数据时次: " + dataTime
              + "\n应收收据个数: " + shouldCount
              + "\n实收数据个数: " + actualCount
              + "\n监控状态: **" + status + "**";

      DingTalkLinkRequest request = DingTalkLinkRequest.of("项目更新通知(测试)",
              content,
              "https://github.com/orgs/JavaFamilyClub/projects/3",
              "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF");

      final String response = dingTalkNotifyHandler.notify(request);

      System.out.println(response);
   }

   @Test
   void testNotifyMarkdown() {
      String dataTime = "2022-06-05 23:00:00";
      int shouldCount = 20, actualCount = 20;
      String status = actualCount < shouldCount ? "异常" : "正常";

      String content = "@18829346477 数据时次: " + dataTime
              + "\n应收收据个数: " + shouldCount
              + "\n实收数据个数: " + actualCount
              + "\n监控状态: **" + status + "**";

      DingTalkMarkDownRequest request = DingTalkMarkDownRequest.of("项目更新通知(测试)",
              content, false, "18829346477");

      final String response = dingTalkNotifyHandler.notify(request);

      System.out.println(response);
   }

   @Test
   void testNotifySingleCard() {
      String dataTime = "2022-06-05 23:00:00";
      int shouldCount = 20, actualCount = 20;
      String status = actualCount < shouldCount ? "异常" : "正常";

      String content = "数据时次: " + dataTime
         + "\n应收收据个数: " + shouldCount
         + "\n实收数据个数: " + actualCount
         + "\n监控状态: **" + status + "**";

      final DingTalkSingleBtnCardRequest request
         = DingTalkSingleBtnCardRequest.of("测试xxx数据监控", content,
         "立即前往系统查看",
         "https://github.com/orgs/JavaFamilyClub/projects/3");

      final String response = dingTalkNotifyHandler.notify(request);

      System.out.println(response);
   }

   @Test
   void testNotifyMultiCard() {
      String content = "测试 xxx 点位 (33.3, 107.7) 已经添加至用户点位库, 是否加入资源池?";

      final DingTalkMultiBtnCardRequest request
              = DingTalkMultiBtnCardRequest.of("点位审核", content,
              CardBtn.builder()
                      .title("接受")
                      .actionURL("https://github.com/orgs/JavaFamilyClub/projects/3")
                      .build(),
              CardBtn.builder()
                      .title("拒绝")
                      .actionURL("https://github.com/JavaFamilyClub/javafamily-utils/actions")
                      .build());

      final String response = dingTalkNotifyHandler.notify(request);

      System.out.println(response);
   }

   @Test
   void testNotifyFeedCard() {
      final DingTalkFeedCardRequest request
              = DingTalkFeedCardRequest.of(FeedCardRequestContentLink.builder()
                      .title("Notification Manager(测试)")
                      .messageURL("https://github.com/orgs/JavaFamilyClub/projects/3")
                      .picURL("https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png")
              .build(),
              FeedCardRequestContentLink.builder()
                      .title("JavaFamily Utils")
                      .messageURL("https://github.com/JavaFamilyClub/javafamily-utils/actions")
                      .picURL("https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF")
                      .build(),
              FeedCardRequestContentLink.builder()
                      .title("JavaFamily Parent Bom")
                      .messageURL("https://github.com/JavaFamilyClub/javafamily-parent/actions")
                      .picURL("https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF")
                      .build());

      final String response = dingTalkNotifyHandler.notify(request);

      System.out.println(response);
   }

}
