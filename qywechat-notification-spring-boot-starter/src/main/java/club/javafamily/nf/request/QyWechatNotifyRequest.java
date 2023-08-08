package club.javafamily.nf.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * @author Jack Li
 * @date 2023/8/5 下午10:51
 * @description 请求消息实体
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class QyWechatNotifyRequest implements NotifyRequest {

   private static final long serialVersionUID = 1L;

   protected String msgtype;

   public QyWechatNotifyRequest() {
   }

   public QyWechatNotifyRequest(String msgtype) {
      this.msgtype = msgtype;
   }

   /**
    * 消息类型
    * @return
    */
   public String getMsgtype() {
      return msgtype;
   }
}
