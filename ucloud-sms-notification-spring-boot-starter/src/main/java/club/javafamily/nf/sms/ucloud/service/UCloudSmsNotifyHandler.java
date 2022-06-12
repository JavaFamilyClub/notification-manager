package club.javafamily.nf.sms.ucloud.service;

import club.javafamily.nf.properties.SmsTemplateInfo;
import club.javafamily.nf.service.NotifyHandler;
import club.javafamily.nf.sms.ucloud.properties.SmsUCloudProperties;
import club.javafamily.nf.sms.ucloud.request.SmsRequest;
import club.javafamily.utils.common.MessageException;
import cn.ucloud.common.pojo.Account;
import cn.ucloud.usms.client.DefaultUSMSClient;
import cn.ucloud.usms.client.USMSClient;
import cn.ucloud.usms.model.SendUSMSMessageParam;
import cn.ucloud.usms.model.SendUSMSMessageResult;
import cn.ucloud.usms.pojo.USMSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jack Li
 * @date 2022/3/3 3:46 下午
 * @description
 */
@Slf4j
public class UCloudSmsNotifyHandler implements NotifyHandler<SmsRequest, SendUSMSMessageResult> {

   private final SmsUCloudProperties properties;

   public UCloudSmsNotifyHandler(SmsUCloudProperties properties) {
      this.properties = properties;
   }

   public SendUSMSMessageResult notify(List<String> phoneNumbers,
                                       List<String> params)
   {
      return notify(SmsUCloudProperties.DEFAULT, phoneNumbers, params);
   }

   /**
    * 发送短信
    * @param
    * @param phoneNumbers 新增接收者
    * @return
    */
   @Nullable
   public SendUSMSMessageResult notify(String template,
                                       List<String> phoneNumbers,
                                       List<String> params)
   {
      final SmsTemplateInfo templateInfo = properties.findConfig(template);

      if(templateInfo == null) {
         throw new MessageException("未配置模板: " + template);
      }

      List<String> receiveUsers = Optional.ofNullable(
         templateInfo.getSafeReceiveUsers()).orElse(new ArrayList<>());

      if(!CollectionUtils.isEmpty(phoneNumbers)) {
         receiveUsers.addAll(phoneNumbers);
      }

      final SmsRequest request = SmsRequest.builder()
         .namespaceId(properties.getProjectId())
         .secretId(properties.getPublicKey())
         .secretKey(properties.getPrivateKey())
         .templateId(templateInfo.getTemplateId())
         .sign(properties.getSign())
         .params(params)
         .receiveUsers(receiveUsers)
         .build();

      return notify(request);
   }

   @Nullable
   @Override
   public SendUSMSMessageResult notify(SmsRequest request) {
      final USMSConfig ucloudConfig = new USMSConfig(
         new Account(
            request.getSecretKey(),
            request.getSecretId()
         )
      );

      SendUSMSMessageResult result = null;

      try {
         USMSClient client = new DefaultUSMSClient(ucloudConfig);

         List<String> receiveUsers = request.getSafeReceiveUsers();

         if(CollectionUtils.isEmpty(receiveUsers)) {
            log.warn("No sms receive users!");
            return null;
         }

         receiveUsers = receiveUsers.stream()
            .map(phone -> phone.startsWith("+")
               ? phone : ("+86" + phone))
            .distinct()
            .collect(Collectors.toList());

         SendUSMSMessageParam param = new SendUSMSMessageParam(
            receiveUsers, request.getTemplateId());

         param.setSigContent(request.getSign());
         param.setProjectId(request.getNamespaceId());
         param.setTemplateParams(request.getParams());

         result = client.sendUSMSMessage(param);
      } catch (Exception e) {
         log.error("Sms send error!", e);
      }

      return result;
   }
}

