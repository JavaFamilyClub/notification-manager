package javafamily.demo;

import club.javafamily.nf.request.FeiShuTextNotifyRequest;
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

}
