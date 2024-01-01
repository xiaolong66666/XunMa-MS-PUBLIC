package com.xunma.common.mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class MailServiceUtil{
    @Autowired
    private JavaMailSender javaMailSender;
    //发送人
    private String from="2636822826@qq.com";
    //标题
    private String title="寻码科技";
    //正文
    private String context="平台已收到新订单，请及时处理！管理平台链接为:http://192.168.1.7/login?redirect=%2Findex";

    public void seedMessage(String to){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(context);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            //做善后处理
            e.printStackTrace();
        }
    }

}
