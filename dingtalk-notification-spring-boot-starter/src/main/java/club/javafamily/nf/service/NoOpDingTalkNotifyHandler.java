package club.javafamily.nf.service;

import club.javafamily.nf.properties.DingTalkProperties;
import club.javafamily.nf.request.DingTalkNotifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * 没有任何操作的 DingTalkNotifyHandler
 */
@Slf4j
public class NoOpDingTalkNotifyHandler extends DingTalkNotifyHandler {

    public NoOpDingTalkNotifyHandler(DingTalkProperties properties) {
        this(properties, null);
    }

    public NoOpDingTalkNotifyHandler(DingTalkProperties properties, RestTemplate restTemplate) {
        super(properties, restTemplate);
    }

    @Override
    public String notify(DingTalkNotifyRequest request) {
        log.info("Received dingTalkNotifyRequest in no op handler! {}", request);

        return "no op";
    }
}
