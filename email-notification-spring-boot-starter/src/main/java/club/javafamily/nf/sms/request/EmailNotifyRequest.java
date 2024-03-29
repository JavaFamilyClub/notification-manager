package club.javafamily.nf.sms.request;

import club.javafamily.nf.request.NotifyRequest;
import club.javafamily.nf.sms.enums.MailType;
import lombok.*;

import java.util.List;

/**
 * @author Jack Li
 * @date 2022/6/13 上午12:32
 * @description
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailNotifyRequest implements NotifyRequest {

   private static final long serialVersionUID = 1L;

   @Builder.Default
   protected MailType type = MailType.SIMPLE;

   protected String from;

   protected String[] to;

   protected String[] cc;

   protected String[] bcc;

   protected String subject;

   protected String content;

   protected List<EmailAttachmentItem> attachments;

   protected List<EmailInlineResourceItem> inlineResource;

}
