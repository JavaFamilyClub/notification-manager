package javafamily.demo;

import club.javafamily.nf.sms.service.TencentSmsNotifyHandler;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
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
public class TencentSmsNotifyTests {

   @Autowired
   private TencentSmsNotifyHandler smsSender;

   @Test
   void testNotify() {
      final SendSmsResponse response = smsSender.notify(
         Collections.singletonList("+8618829346477"),
         "测试:修武县气象台", "测试:大风事件", "修武县气象台2022年03月02日16时25分发布大风蓝色预警信号：预计未来24小时内我县城关镇、郇封镇、周庄镇、五里源乡、王屯乡等乡镇有4到5级的偏西风，阵风6到7级，七贤镇、云台山镇、西村乡等乡镇阵风将达7到8级，请注意防范。（预警信息来源：国家预警信息发布中心）");

      System.out.println(response);
   }

}
