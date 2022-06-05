package club.javafamily.nf.request;

import club.javafamily.nf.request.card.*;
import club.javafamily.nf.request.tags.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;

/**
 * @author Jack Li
 * @date 2022/6/5 下午6:12
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeiShuCardNotifyRequest extends FeiShuNotifyRequest {
   private FeiShuCardRequestContent card;

   public static FeiShuCardNotifyRequest of(String title,
                                            String mdText,
                                            String buttonText,
                                            String buttonUrl)
   {
      return FeiShuCardNotifyRequest.of(
         title, mdText, buttonText, buttonUrl, null);
   }

   public static FeiShuCardNotifyRequest of(String title,
                                            String mdText,
                                            String buttonText,
                                            String buttonUrl,
                                            String buttonType)
   {
      final FeiShuCardRequestHeader header
         = new FeiShuCardRequestHeader(new PlainTextTagContentItem(title));

      final FeiShuCardRequestConfig config = new FeiShuCardRequestConfig();

      final DivMdTagContentItem div = new DivMdTagContentItem(mdText);

      final ActionTagContentItem action
         = new ActionTagContentItem(buttonText, buttonUrl, buttonType);

      return FeiShuCardNotifyRequest.of(header, config,
         Arrays.asList(div, action));
   }

   public static FeiShuCardNotifyRequest of(FeiShuCardRequestHeader header,
                                            FeiShuCardRequestConfig config,
                                            List<TagContentItem> elements)
   {
      final FeiShuCardRequestContent cardContent
         = FeiShuCardRequestContent.builder()
         .header(header)
         .config(config)
         .elements(elements)
         .build();

      final FeiShuCardNotifyRequest request = new FeiShuCardNotifyRequest();
      request.setCard(cardContent);

      return request;
   }

   @Override
   public String getMsg_type() {
      return "interactive";
   }

   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public static class FeiShuImageContent implements Serializable {
      protected String image_key;
   }

}
