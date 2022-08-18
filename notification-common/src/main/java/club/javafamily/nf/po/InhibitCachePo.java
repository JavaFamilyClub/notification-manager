package club.javafamily.nf.po;

import club.javafamily.nf.request.NotifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InhibitCachePo implements Serializable {

    private NotifyRequest request;

    private String response;

}
