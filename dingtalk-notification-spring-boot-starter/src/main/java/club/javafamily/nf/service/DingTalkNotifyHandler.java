package club.javafamily.nf.service;

import club.javafamily.nf.enums.NotifySupportTypeEnum;
import club.javafamily.nf.properties.DingTalkProperties;
import club.javafamily.nf.request.DingTalkNotifyRequest;
import club.javafamily.nf.request.NotifyRequest;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:26
 * @description 钉钉机器人通知处理器
 */
public class DingTalkNotifyHandler extends BaseWebHookNotifyHandler<DingTalkNotifyRequest> {

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

    /**
     * 自身的类型
     *
     * @return NotifySupportTypeEnum
     */
    @Override
    public NotifySupportTypeEnum selfType() {
        return NotifySupportTypeEnum.DING_TALK;
    }

    @Override
    public String notify(DingTalkNotifyRequest request) {
        return postForJson(
           properties.getHookUrl(), request, String.class);
    }
}
