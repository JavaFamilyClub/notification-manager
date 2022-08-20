package club.javafamily.nf.service;

import club.javafamily.autoconfigre.cache.service.CacheOperator;
import club.javafamily.nf.enums.NotifySupportTypeEnum;
import club.javafamily.nf.po.InhibitCachePo;
import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.request.FeiShuImageNotifyRequest;
import club.javafamily.nf.request.FeiShuNotifyRequest;
import club.javafamily.nf.service.inhibit.policy.DefaultInhibitPolicy;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:26
 * @description
 */
public class FeiShuNotifyHandler extends BaseWebHookNotifyHandler<FeiShuNotifyRequest> {

    private final FeiShuProperties properties;
    private final InhibitPolicy inhibitPolicy;

    public FeiShuNotifyHandler(FeiShuProperties properties,
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
        return NotifySupportTypeEnum.FEI_SHU;
    }

    @Override
    public String notify(FeiShuNotifyRequest request) {
        // inhibited
        if(inhibitPolicy != null && inhibitPolicy.isInhibited(request)) {
            return InhibitRule.INHIBIT_RESPONSE;
        }

        if(request instanceof FeiShuImageNotifyRequest) {
            final FeiShuImageNotifyRequest.FeiShuImageContent content
               = ((FeiShuImageNotifyRequest) request).getContent();

            if(content instanceof FeiShuImageNotifyRequest.FeiShuImageStreamContent
                && ObjectUtils.isEmpty(content.getImage_key()))
            {
//                final InputStream in = ((FeiShuImageNotifyRequest
//                   .FeiShuImageStreamContent) content).getImageStream();

                // TODO upload image: this is need to authrization on feishu platform.

                throw new UnsupportedOperationException("Not supported for now, it is being improved.");
            }
        }

        String response = postForJson(properties.getHookUrl(), request, String.class);

        if (inhibitPolicy != null) {
            inhibitPolicy.completeInhibited(request, response);
        }

        return response;
    }
}
