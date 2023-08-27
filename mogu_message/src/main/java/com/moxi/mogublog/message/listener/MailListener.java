package com.moxi.mogublog.message.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * 邮件监听器，消息消费者
 * 踩坑：要用 Component 注解标记，被spring管理才能生效
 * @author LiuYiChang
 * @date 2023/8/8 20:32
 */

@Slf4j
@Component
public class MailListener {

    @Value(value = "${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitListener(queues = "mogu.email")
    public void sendMail(Map<String,String> map){
        //创建消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject(map.get("subject"));
            helper.setText(map.get("text"));
            //邮件发送者
            helper.setFrom(sender);
            //邮件接收者
            helper.setTo(map.get("receiver"));
            javaMailSender.send(mimeMessage);
            log.info("邮件发送成功");
            //TODO 添加邮件附件
            //helper.addAttachment
        }catch (Exception e){
            log.error(e.getMessage());
        }

        }

}
