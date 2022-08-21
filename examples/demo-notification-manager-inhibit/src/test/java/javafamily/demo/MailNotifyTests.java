package javafamily.demo;

import club.javafamily.nf.sms.service.EmailNotifyHandler;
import org.jasypt.encryption.StringEncryptor;
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

    @Autowired
    private StringEncryptor encryption;

    @Test
    void testNotifySimple() throws Exception {
        emailNotifyHandler.sendSimpleMailMessage("测试案例", "这是一个测试内容",
                "243853974@qq.com", "18829346477@163.com");

        System.out.println("ok");
    }

    @Test
    void testNotifyMime() throws Exception {
        emailNotifyHandler.sendMimeMessageWithLocaleFile("测试案例", "这是一个测试内容",
                "E:\\Sunny\\react_资料\\代码资料.zip",
                "243853974@qq.com", "18829346477@163.com");

        System.out.println("ok");
    }

    @Test
    public void generatorEnvPassword() {
        String password = "dreamli0812";

        System.out.println(encryption.encrypt(password));
    }
}
