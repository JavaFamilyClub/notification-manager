package club.javafamily.nf.service;

import club.javafamily.nf.properties.DingTalkProperties;
import club.javafamily.nf.request.DingTalkNotifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2023/3/26 上午12:15
 * @description 没有任何操作的 DingTalkNotifyHandler
 */
@Slf4j
public class NoOpDingTalkNotifyHandler extends DingTalkNotifyHandler {

    public NoOpDingTalkNotifyHandler(DingTalkProperties properties) {
        this(properties, null);
    }

    public NoOpDingTalkNotifyHandler(DingTalkProperties properties, RestTemplate restTemplate) {
        super(properties, restTemplate, null);
    }

    @Override
    public String notify(DingTalkNotifyRequest request) {
        log.info("Received dingTalkNotifyRequest in no op handler! {}", request);

        return "no op";
    }
}
