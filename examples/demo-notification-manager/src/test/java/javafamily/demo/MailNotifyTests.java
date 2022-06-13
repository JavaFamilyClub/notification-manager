package javafamily.demo;

import club.javafamily.nf.sms.service.EmailNotifyHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Jack Li
 * @date 2022/6/4 下午11:03
 * @description
 */
@SpringBootTest
public class MailNotifyTests {

    @Autowired
    private EmailNotifyHandler emailNotifyHandler;

    @Test
    void testNotifySimple() throws Exception {
        emailNotifyHandler.sendSimpleMailMessage("测试案例", "这是一个测试内容",
                "243853974@qq.com", "18829346477@163.com");

        System.out.println("ok");
    }

    @Test
    void testNotifyMime() throws Exception {
        emailNotifyHandler.sendMimeMessage("测试案例", "这是一个测试内容",
                "243853974@qq.com", "18829346477@163.com");

        System.out.println("ok");
    }
}
