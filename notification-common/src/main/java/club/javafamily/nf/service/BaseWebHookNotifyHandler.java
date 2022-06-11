package club.javafamily.nf.service;

import club.javafamily.nf.request.NotifyRequest;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:26
 * @description
 */
public abstract class BaseWebHookNotifyHandler<NR extends NotifyRequest> implements NotifyHandler<NR> {

    protected final RestTemplate restTemplate;

    public BaseWebHookNotifyHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    public String getHookUrl() {
//        throw new UnsupportedOperationException("Unsupported Operation!");
//    }

    public abstract String getHookUrl();

    @Override
    public String notify(NR request) {
        return restTemplate.postForObject(
                getHookUrl(), request, String.class);
    }
}
