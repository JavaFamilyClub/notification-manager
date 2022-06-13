package club.javafamily.nf.sms.request;

import club.javafamily.nf.sms.enums.ResourceTypeEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/6/13 上午12:48
 * @description
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailAttachmentItem implements Serializable {

   private String name;

   private Object source;

   private ResourceTypeEnum type;
}
