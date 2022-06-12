package club.javafamily.nf.sms.tencent.service;

import club.javafamily.nf.service.NotifyHandler;
import club.javafamily.nf.sms.tencent.enums.MailType;
import club.javafamily.nf.sms.tencent.enums.ResourceTypeEnum;
import club.javafamily.nf.sms.tencent.request.*;
import club.javafamily.utils.common.MessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
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
   {
      notify(EmailNotifyRequest.builder()
         .from(properties.getUsername())
         .to(to)
         .type(MailType.MIME)
         .subject(subject)
         .content(content)
         .build());
   }

   public void sendMimeMessage(String subject,
                               String content,
                               String filePath,
                               String...to)
      throws MessagingException
   {
      FileSystemResource file = new FileSystemResource(new File(filePath));

      notify(EmailNotifyRequest.builder()
         .from(properties.getUsername())
         .to(to)
         .type(MailType.MIME)
         .subject(subject)
         .content(content)
         .attachments(Collections.singletonList(
            EmailAttachmentItem.builder()
               .name(file.getFilename())
               .source(file)
               .build()))
         .build());
   }

   /**
    * 邮件短信
    * @param request 请求参数
    * @return 邮件发送响应
    */
   @Override
   public Void notify(EmailNotifyRequest request) {
      if (request.getType() == MailType.SIMPLE) {
         sendSimpleEmail(request);
      } else {
         sendMimeEmail(request);
      }

      return null;
   }

   private void sendMimeEmail(EmailNotifyRequest request) {
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
               Resource resource = buildResource(item);

               if(resource == null) {
                  throw new MessageException("Inline resource getting failed: " + item);
               }

               helper.addInline(item.getId(), resource);
            }
         }

         if(!CollectionUtils.isEmpty(request.getAttachments())) {
            for (EmailAttachmentItem attachment : request.getAttachments()) {
               helper.addAttachment(
                  attachment.getName(), attachment.getSource());
            }
         }

         mailSender.send(message);
      }
      catch (Exception e) {
         log.error("Send email failed!", e);
      }
   }

   private Resource buildResource(EmailInlineResourceItem item)
      throws MalformedURLException
   {
      if(item.getType() == ResourceTypeEnum.LOCALE_FILE
         && item.getSource() instanceof File)
      {
         return new FileSystemResource((File) item.getSource());
      }
      else if(item.getType() == ResourceTypeEnum.LOCALE_FILE_PATH
         && item.getSource() instanceof String)
      {
         return new FileSystemResource(new File((String) item.getSource()));
      }
      else if(item.getType() == ResourceTypeEnum.URL
         && item.getSource() instanceof String)
      {
         return new UrlResource((String) item.getSource());
      }
      else if(item.getType() == ResourceTypeEnum.STREAM
         && item.getSource() instanceof InputStream)
      {
         return new InputStreamResource((InputStream) item.getSource());
      }
      else if(item.getType() == ResourceTypeEnum.BYTE_ARRAY
         && item.getSource() instanceof byte[])
      {
         byte[] bytes = (byte[]) item.getSource();
         return new InputStreamResource(new ByteArrayInputStream(bytes));
      }

      return null;
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

