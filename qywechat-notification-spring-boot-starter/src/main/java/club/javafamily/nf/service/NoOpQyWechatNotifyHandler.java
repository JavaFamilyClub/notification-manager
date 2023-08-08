package club.javafamily.nf.service;

import club.javafamily.nf.properties.QyWechatProperties;
import club.javafamily.nf.request.QyWechatNotifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2023/3/26 上午12:16
 * @description 没有任何操作的 FeiShuNotifyHandler
 */
@Slf4j
public class NoOpQyWechatNotifyHandler extends QyWechatNotifyHandler {

    private static final String NOOP_RESPONSE = "no op";

    public NoOpQyWechatNotifyHandler(QyWechatProperties properties) {
        this(properties, null);
    }

    public NoOpQyWechatNotifyHandler(QyWechatProperties properties, RestTemplate restTemplate) {
        super(properties, restTemplate, null);
    }

    @Override
    public String notify(QyWechatNotifyRequest request) {
        log.info("Received feiShuNotifyRequest in no op handler! {}", request);

        return NOOP_RESPONSE;
    }
}
