package club.javafamily.nf.service;

import club.javafamily.nf.enums.NotifySupportTypeEnum;
import club.javafamily.nf.properties.QyWechatProperties;
import club.javafamily.nf.request.QyWechatNotifyRequest;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:26
 * @description
 */
public class QyWechatNotifyHandler extends BaseWebHookNotifyHandler<QyWechatNotifyRequest> {

    private final QyWechatProperties properties;
    private final InhibitPolicy inhibitPolicy;

    public QyWechatNotifyHandler(QyWechatProperties properties,
                                 RestTemplate restTemplate,
                                 InhibitPolicy inhibitPolicy)
    {
        super(restTemplate);
        this.properties = properties;
        this.inhibitPolicy = inhibitPolicy;
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
        return NotifySupportTypeEnum.QY_WECHAT;
    }

    @Override
    public String notify(QyWechatNotifyRequest request) {
        // inhibited
        if(inhibitPolicy != null && inhibitPolicy.isInhibited(request)) {
            return InhibitRule.INHIBIT_RESPONSE;
        }

        String response = postForJson(properties.getHookUrl(), request, String.class);

        if (inhibitPolicy != null) {
            inhibitPolicy.completeInhibited(request, response);
        }

        return response;
    }
}
