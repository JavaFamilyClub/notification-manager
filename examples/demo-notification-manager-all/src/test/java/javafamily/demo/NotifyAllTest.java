package javafamily.demo;

import club.javafamily.nf.enums.NotifySupportTypeEnum;
import club.javafamily.nf.request.text.DingTalkTextNotifyRequest;
import club.javafamily.nf.service.NotifyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotifyAllTest {

    @Autowired
    private NotifyService notifyService;

    @Test
    void contextLoad() {
        Assertions.assertNotNull(notifyService);
    }

    @Test
    void testNotifyOneType() {
        Object response = notifyService.notify(NotifySupportTypeEnum.DING_TALK,
                DingTalkTextNotifyRequest.of("这是一个测试数据!"),
                DingTalkTextNotifyRequest.of("这是两个测试数据!"));

        System.out.println(response);
    }

//    @Test
//    void testNotifyMultiType() {
//        Object response = notifyService.notify(NotifySupportTypeEnum.DING_TALK,
//                DingTalkTextNotifyRequest.of("这是一个测试数据!"));
//
//        System.out.println(response);
//    }

}
