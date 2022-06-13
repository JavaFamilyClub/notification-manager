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
@ToString
public class EmailInlineResourceItem implements Serializable {

   private String id;

   private Object source;

   private ResourceTypeEnum type;
}
