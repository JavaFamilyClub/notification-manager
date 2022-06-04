package club.javafamily.nf.service;

import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.request.FeiShuNotifyRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FeiShuNotifyHandler implements NotifyHandler<FeiShuNotifyRequest> {

    private final FeiShuProperties feiShuProperties;
    private final RestTemplate restTemplate;

    public FeiShuNotifyHandler(FeiShuProperties feiShuProperties,
                               RestTemplate restTemplate)
    {
        this.feiShuProperties = feiShuProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void notify(FeiShuNotifyRequest request) {

    }
}
