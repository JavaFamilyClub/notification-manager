package club.javafamily.nf.service;

import club.javafamily.nf.properties.DingTalkProperties;
import club.javafamily.nf.request.NotifyRequest;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:26
 * @description 钉钉机器人通知处理器
 */
public class DingTalkNotifyHandler extends BaseWebHookNotifyHandler<NotifyRequest> {

    private final DingTalkProperties properties;

    public DingTalkNotifyHandler(DingTalkProperties properties,
                                 RestTemplate restTemplate)
    {
        super(restTemplate);
        this.properties = properties;
    }

    @Override
    public String getHookUrl() {
        return properties.getHookUrl();
    }

    @Override
    public String notify(NotifyRequest request) {
        return restTemplate.postForObject(
           properties.getHookUrl(), request, String.class);
    }
}
