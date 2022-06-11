package club.javafamily.nf.service;

import club.javafamily.nf.properties.WebHookProperties;
import club.javafamily.nf.request.BaseFeiShuNotifyRequest;
import club.javafamily.nf.request.FeiShuImageNotifyRequest;
import club.javafamily.nf.request.NotifyRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:26
 * @description
 */
public class FeiShuNotifyHandler extends BaseWebHookNotifyHandler<BaseFeiShuNotifyRequest> {

    private final WebHookProperties properties;
    private final RestTemplate restTemplate;

    public FeiShuNotifyHandler(WebHookProperties properties,
                               RestTemplate restTemplate)
    {
        super(restTemplate);
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getHookUrl() {
        return properties.getHookUrl();
    }

    @Override
    public String notify(BaseFeiShuNotifyRequest request) {
        if(request instanceof FeiShuImageNotifyRequest) {
            final FeiShuImageNotifyRequest.FeiShuImageContent content
               = ((FeiShuImageNotifyRequest) request).getContent();

            if(content instanceof FeiShuImageNotifyRequest.FeiShuImageStreamContent
                && ObjectUtils.isEmpty(content.getImage_key()))
            {
                final InputStream in = ((FeiShuImageNotifyRequest
                   .FeiShuImageStreamContent) content).getImageStream();

                // TODO upload image: this is need to authrization on feishu platform.

                throw new UnsupportedOperationException("Not supported for now, it is being improved.");
            }
        }

        return restTemplate.postForObject(
           properties.getHookUrl(), request, String.class);
    }
}
