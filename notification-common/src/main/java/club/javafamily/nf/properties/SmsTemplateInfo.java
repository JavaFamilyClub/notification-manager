package club.javafamily.nf.properties;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Jack Li
 * @date 2022/3/7 2:53 下午
 * @description
 */
@Data
public class SmsTemplateInfo implements Serializable {
   private String templateId;
   private List<String> receiveUsers;

   @NonNull
   public List<String> getSafeReceiveUsers() {
      return receiveUsers != null
         ? new CopyOnWriteArrayList<>(receiveUsers)
         : new ArrayList<>();
   }
}
