package club.javafamily.nf.sms.tencent.service;

import club.javafamily.nf.constant.NotificationConstant;
import club.javafamily.nf.properties.SmsTemplateInfo;
import club.javafamily.nf.request.sms.SmsRequest;
import club.javafamily.nf.service.NotifyHandler;
import club.javafamily.nf.sms.tencent.properties.SmsTencentProperties;
import club.javafamily.utils.common.MessageException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jack Li
 * @date 2022/3/3 3:46 下午
 * @description
 */
@Slf4j
public class TencentSmsNotifyHandler implements NotifyHandler<SmsRequest, SendSmsResponse> {

   private final SmsTencentProperties properties;

   public TencentSmsNotifyHandler(SmsTencentProperties properties) {
      this.properties = properties;
   }

   public SendSmsResponse notify(List<String> phoneNumbers,
                                       String...params)
   {
      return notify(NotificationConstant.DEFAULT, phoneNumbers,
         params == null ? null : Arrays.asList(params));
   }

   public SendSmsResponse notify(List<String> phoneNumbers,
                                       List<String> params)
   {
      return notify(NotificationConstant.DEFAULT, phoneNumbers, params);
   }

   @Nullable
   public SendSmsResponse notify(String template,
                                       List<String> phoneNumbers,
                                       String...params)
   {
      return notify(template, phoneNumbers,
         params == null ? null : Arrays.asList(params));
   }

   /**
    * 短信通知
    * @param template 模板配置 key
    * @param phoneNumbers 附加接受人
    * @param params 短信参数
    * @return 短信发送回执
    */
   @Nullable
   public SendSmsResponse notify(String template,
                                 List<String> phoneNumbers,
                                 List<String> params)
   {
      final SmsTemplateInfo templateInfo = properties.findConfig(template);

      if(templateInfo == null) {
         throw new MessageException("未配置模板: " + template);
      }

      List<String> receiveUsers = templateInfo.getSafeReceiveUsers();

      if(!CollectionUtils.isEmpty(phoneNumbers)) {
         receiveUsers.addAll(phoneNumbers);
      }

      final SmsRequest request
         = properties.buildRequest(template, receiveUsers, params);

      return notify(request);
   }

   /**
    * 发送短信
    * @param request 请求参数
    * @return 短信发送回执
    */
   @Nullable
   @Override
   public SendSmsResponse notify(SmsRequest request) {
      String appUrl = request.getEndPoint();
      String sdkAppId = request.getNamespaceId();
      String secretId = request.getSecretId();
      String secretKey = request.getSecretKey();

      SendSmsResponse res = new SendSmsResponse();

      try {
         Credential cred = new Credential(secretId, secretKey);
         HttpProfile httpProfile = new HttpProfile();
         httpProfile.setReqMethod("POST");
         httpProfile.setConnTimeout(request.getTimeout());
         /* SDK 会自动指定域名，通常无需指定域名，但访问金融区的服务时必须手动指定域名
          * 例如 SMS 的上海金融区域名为 sms.ap-shanghai-fsi.tencentcloudapi.com */
         httpProfile.setEndpoint(appUrl);
         /* 非必要步骤:
          * 实例化一个客户端配置对象，可以指定超时时间等配置 */
         ClientProfile clientProfile = new ClientProfile();
         /* SDK 默认用 TC3-HMAC-SHA256 进行签名
          * 非必要请不要修改该字段 */
         clientProfile.setSignMethod("HmacSHA256");
         clientProfile.setHttpProfile(httpProfile);
         /* 实例化 SMS 的 client 对象
          * 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量 */
         SmsClient client = new SmsClient(cred, "ap-shanghai");
         client.setClientProfile(clientProfile);

         SendSmsRequest req = new SendSmsRequest();

         req.setSmsSdkAppid(sdkAppId);

         /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 */
         String sign = properties.getSign(); //短信签名
         req.setSign(sign);

         req.setTemplateID(request.getTemplateId());
         String[] params = request.getParams() != null
            ? request.getParams().toArray(new String[0])
            : null;
         req.setTemplateParamSet(params);

         List<String> receiveUsers = request.getSafeReceiveUsers();

         if(CollectionUtils.isEmpty(receiveUsers)) {
            log.warn("No sms receive users for tencent sms!");
            return null;
         }

         receiveUsers = receiveUsers.stream()
            .map(phone -> phone.startsWith("+")
               ? phone : ("+86" + phone))
            .distinct()
            .collect(Collectors.toList());

         req.setPhoneNumberSet(receiveUsers.toArray(new String[0]));

         res = client.SendSms(req);
      } catch (TencentCloudSDKException e) {
        log.error("Send tencent sms failed!", e);
      }

      return res;
   }
}

