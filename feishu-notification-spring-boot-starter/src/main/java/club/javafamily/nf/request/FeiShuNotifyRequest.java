package club.javafamily.nf.request;

import lombok.Data;

/**
 * @author Jack Li
 * @date 2022/6/4 下午10:51
 * @description 文本消息
 */
@Data
public abstract class FeiShuNotifyRequest implements NotifyRequest {

   private static final long serialVersionUID = 1L;

   protected String msg_type;

   public FeiShuNotifyRequest() {
   }

   public FeiShuNotifyRequest(String msg_type) {
      this.msg_type = msg_type;
   }

   /**
    * 消息类型
    * @return
    */
   public String getMsg_type() {
      return msg_type;
   }
}
