package club.javafamily.nf.service;

import club.javafamily.nf.properties.FeiShuProperties;
import club.javafamily.nf.request.FeiShuNotifyRequest;
import org.springframework.stereotype.Component;

@Component
public class FeiShuNotifyHandler implements NotifyHandler<FeiShuNotifyRequest> {

    private final FeiShuProperties feiShuProperties;

    public FeiShuNotifyHandler(FeiShuProperties feiShuProperties) {
        this.feiShuProperties = feiShuProperties;
    }

    @Override
    public void notify(FeiShuNotifyRequest request) {

    }
}
