package club.javafamily.nf.service;

import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.request.FeiShuNotifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * 没有任何操作的 FeiShuNotifyHandler
 */
@Slf4j
public class NoOpFeiShuNotifyHandler extends FeiShuNotifyHandler {

    public NoOpFeiShuNotifyHandler(FeiShuProperties properties) {
        this(properties, null);
    }

    public NoOpFeiShuNotifyHandler(FeiShuProperties properties, RestTemplate restTemplate) {
        super(properties, restTemplate);
    }

    @Override
    public String notify(FeiShuNotifyRequest request) {
        log.info("Received feiShuNotifyRequest in no op handler! {}", request);

        return "no op";
    }
}
