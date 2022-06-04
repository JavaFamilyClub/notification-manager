package javafamily.demo;

import club.javafamily.nf.request.FeiShuPostNotifyRequest;
import club.javafamily.nf.request.FeiShuTextNotifyRequest;
import club.javafamily.nf.request.post.LinkPostTagContentItem;
import club.javafamily.nf.request.post.PostTagContentItem;
import club.javafamily.nf.service.FeiShuNotifyHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:03
 * @description
 */
@SpringBootTest
public class ApplicationTests {

   @Autowired
   private FeiShuNotifyHandler notifyHandler;

   @Test
   void contextLoad() {
      Assertions.assertNotNull(notifyHandler);
   }

   @Test
   void testNotifyText() {
      final String response = notifyHandler.notify(
         FeiShuTextNotifyRequest.of("这是一个测试数据!"));

      System.out.println(response);
   }

   @Test
   void testNotifyPost() {
      final FeiShuPostNotifyRequest request = FeiShuPostNotifyRequest.of(
         "项目更新通知(标题)",
         new PostTagContentItem("(测试)项目有更新: "),
         new LinkPostTagContentItem("请查看",
            "https://github.com/orgs/JavaFamilyClub/projects/3"));

      final String response = notifyHandler.notify(request);

      System.out.println(response);
   }
}
