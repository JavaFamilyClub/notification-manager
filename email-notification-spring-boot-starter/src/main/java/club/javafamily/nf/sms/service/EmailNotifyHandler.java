package club.javafamily.nf.sms.service;

import club.javafamily.nf.service.NotifyHandler;
import club.javafamily.nf.sms.enums.MailType;
import club.javafamily.nf.sms.enums.ResourceTypeEnum;
import club.javafamily.nf.sms.request.EmailAttachmentItem;
import club.javafamily.nf.sms.request.EmailInlineResourceItem;
import club.javafamily.nf.sms.request.EmailNotifyRequest;
import club.javafamily.utils.common.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Collections;

/**
 * @author Jack Li
 * @date 2022/3/3 3:46 下午
 * @description
 */
@Slf4j
public class EmailNotifyHandler implements NotifyHandler<EmailNotifyRequest, Void> {

   private final JavaMailSender mailSender;
   private final MailProperties properties;

   public EmailNotifyHandler(JavaMailSender mailSender,
                             MailProperties properties)
   {
      this.mailSender = mailSender;
      this.properties = properties;
   }

   public void sendSimpleMailMessage(String subject,
                                     String content,
                                     String...to)
           throws Exception
   {
      notify(EmailNotifyRequest.builder()
         .from(properties.getUsername())
         .to(to)
         .type(MailType.SIMPLE)
         .subject(subject)
         .content(content)
         .build());
   }

   public void sendMimeMessage(String subject,
                               String content,
                               String...to)
           throws Exception
   {
      notify(EmailNotifyRequest.builder()
         .from(properties.getUsername())
         .to(to)
         .type(MailType.MIME)
         .subject(subject)
         .content(content)
         .build());
   }

   public void sendMimeMessageWithStream(String subject,
                                         String content,
                                         String attachName,
                                         InputStream attachStream,
                                         String...to)
           throws Exception
   {
      notify(EmailNotifyRequest.builder()
              .from(properties.getUsername())
              .to(to)
              .type(MailType.MIME)
              .subject(subject)
              .content(content)
              .attachments(Collections.singletonList(
                      EmailAttachmentItem.builder()
                              .name(attachName)
                              .source(attachStream)
                              .type(ResourceTypeEnum.STREAM)
                              .build()))
              .build());
   }

   public void sendMimeMessageWithLocaleFile(String subject,
                                             String content,
                                             String filePath,
                                             String...to)
           throws Exception
   {
      notify(EmailNotifyRequest.builder()
         .from(properties.getUsername())
         .to(to)
         .type(MailType.MIME)
         .subject(subject)
         .content(content)
         .attachments(Collections.singletonList(
            EmailAttachmentItem.builder()
               .name(new File(filePath).getName())
               .source(filePath)
               .type(ResourceTypeEnum.LOCALE_FILE_PATH)
               .build()))
         .build());
   }

   /**
    * 邮件短信
    * @param request 请求参数
    * @return 邮件发送响应
    */
   @Override
   public Void notify(EmailNotifyRequest request) throws Exception {
      if (request.getType() == MailType.SIMPLE) {
         sendSimpleEmail(request);
      } else {
         sendMimeEmail(request);
      }

      return null;
   }

   private void sendMimeEmail(EmailNotifyRequest request) throws Exception {
      MimeMessage message = mailSender.createMimeMessage();

      try {
         MimeMessageHelper helper = new MimeMessageHelper(message, true);

         helper.setFrom(request.getFrom());
         helper.setTo(request.getTo());
         helper.setCc(request.getCc());
         helper.setBcc(request.getBcc());
         helper.setSubject(request.getSubject());
         helper.setText(request.getContent(), true);

         if(!CollectionUtils.isEmpty(request.getInlineResource())) {
            for(EmailInlineResourceItem item : request.getInlineResource()) {
               Resource resource = buildResource(item.getType(), item.getSource());

               if(resource == null) {
                  throw new MessageException("Inline resource getting failed: " + item);
               }

               helper.addInline(item.getId(), resource);
            }
         }

         if(!CollectionUtils.isEmpty(request.getAttachments())) {
            for (EmailAttachmentItem attachment : request.getAttachments()) {
               if(attachment.getType() == null) {
                  if(attachment.getSource() instanceof InputStreamSource) {
                     helper.addAttachment(
                             attachment.getName(), (InputStreamSource) attachment.getSource());
                  }
                  else if(attachment.getSource() instanceof DataSource) {
                     helper.addAttachment(
                             attachment.getName(), (DataSource) attachment.getSource());
                  }
                  else {
                     throw new MessageException("不支持的 Source 类型: " + attachment.getSource());
                  }
               }
               else {
                  Resource resource = buildResource(attachment.getType(), attachment.getSource());

                  if(resource != null) {
                     helper.addAttachment(attachment.getName(), resource);
                  }
               }
            }
         }

         mailSender.send(message);
      }
      catch (MessageException e) {
         throw e;
      }
      catch (Exception e) {
         log.error("Send email failed!", e);
         throw e;
      }
   }

   private Resource buildResource(ResourceTypeEnum type, Object source)
      throws MalformedURLException
   {
      if(type == ResourceTypeEnum.LOCALE_FILE
         && source instanceof File)
      {
         return new FileSystemResource((File) source);
      }
      else if(type == ResourceTypeEnum.LOCALE_FILE_PATH
         && source instanceof String)
      {
         return new FileSystemResource(new File((String) source));
      }
      else if(type == ResourceTypeEnum.URL
         && source instanceof String)
      {
         return new UrlResource((String) source);
      }
      else if(type == ResourceTypeEnum.STREAM
         && source instanceof InputStream)
      {
         return new InputStreamResource((InputStream) source);
      }
      else if(type == ResourceTypeEnum.BYTE_ARRAY
         && source instanceof byte[])
      {
         byte[] bytes = (byte[]) source;
         return new InputStreamResource(new ByteArrayInputStream(bytes));
      }

      log.error("No match source type! type: {}, source: {}", type, source);

      throw new MessageException("不支持的 Source 类型! type:" + type + ", source: " + source);
   }

   private void sendSimpleEmail(EmailNotifyRequest request) {
      SimpleMailMessage message = new SimpleMailMessage();

      message.setFrom(request.getFrom());
      message.setTo(request.getTo());
      message.setCc(request.getCc());
      message.setBcc(request.getBcc());
      message.setSubject(request.getSubject());
      message.setText(request.getContent());

      mailSender.send(message);
   }
}

