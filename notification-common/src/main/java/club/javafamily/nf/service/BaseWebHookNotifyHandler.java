package club.javafamily.nf.service;

import club.javafamily.nf.request.NotifyRequest;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:26
 * @description
 */
public abstract class BaseWebHookNotifyHandler<NR extends NotifyRequest> implements NotifyHandler<NR, String> {

    protected final RestTemplate restTemplate;

    public BaseWebHookNotifyHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * getting robot webhook url
     * @return url
     */
    public abstract String getHookUrl();

    /**
     * 发送 post 请求, 并指定 content type 为 application/json
     */
    protected <T> T postForJson(String url,
                                @Nullable Object request,
                                Class<T> responseType,
                                Object... uriVariables)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(url, entity, responseType, uriVariables);
    }

    @Override
    public String notify(NR request) {
        return postForJson(
           getHookUrl(), request, String.class);
    }
}
