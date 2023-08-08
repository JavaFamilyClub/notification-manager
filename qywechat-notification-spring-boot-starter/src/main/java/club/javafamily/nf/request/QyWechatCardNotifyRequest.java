package club.javafamily.nf.request;

import club.javafamily.nf.request.card.QyWechatCardRequestContent;
import club.javafamily.nf.request.card.QyWechatCardRequestContentCardAction;
import club.javafamily.nf.request.card.QyWechatCardRequestContentQuoteArea;
import club.javafamily.nf.request.content.TitleDescContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QyWechatCardNotifyRequest extends QyWechatNotifyRequest {

   private QyWechatCardRequestContent template_card;

   public QyWechatCardNotifyRequest() {
      super("template_card");
   }

   public QyWechatCardNotifyRequest(QyWechatCardRequestContent content) {
      this();
      this.template_card = content;
   }

   public static QyWechatCardNotifyRequest of(String title, String desc,
                                              String data, String dataDesc,
                                              String quoteTitle, String quoteText,
                                              String cardAction)
   {
      QyWechatCardRequestContent requestContent = new QyWechatCardRequestContent();

      requestContent.setMain_title(TitleDescContent.of(title, desc));
      requestContent.setEmphasis_content(TitleDescContent.of(data, dataDesc));

      requestContent.setQuote_area(QyWechatCardRequestContentQuoteArea.builder()
              .type(0)
              .title(quoteTitle)
              .quote_text(quoteText)
              .build());

      requestContent.setCard_action(QyWechatCardRequestContentCardAction.builder()
              .type(1)
              .url(cardAction)
              .build());

      return new QyWechatCardNotifyRequest(requestContent);
   }
}
