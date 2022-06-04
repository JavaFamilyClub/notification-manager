package club.javafamily.nf.service;

import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.request.FeiShuTextNotifyRequest;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:26
 * @description
 */
public class FeiShuNotifyHandler implements NotifyHandler<FeiShuTextNotifyRequest> {

    private final FeiShuProperties properties;
    private final RestTemplate restTemplate;

    public FeiShuNotifyHandler(FeiShuProperties properties,
                               RestTemplate restTemplate)
    {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String notify(FeiShuTextNotifyRequest request) {
        return restTemplate.postForObject(
           properties.getHookUrl(), request, String.class);
    }
}
