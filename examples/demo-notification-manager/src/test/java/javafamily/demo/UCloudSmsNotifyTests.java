package javafamily.demo;

import club.javafamily.nf.sms.ucloud.service.UCloudSmsNotifyHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @author Jack Li
 * @date 2022/6/12 下午10:51
 * @description
 */
@SpringBootTest
public class UCloudSmsNotifyTests {

   @Autowired
   private UCloudSmsNotifyHandler uCloudSmsNotifyHandler;

   @Test
   void testNotify() {
      uCloudSmsNotifyHandler.notify(
         Collections.singletonList("18829346477"),
         "测试", "25#点位", "47");
   }

}
