package com.xunma.system.service.impl;
import com.xunma.common.core.domain.entity.SysUser;
import com.xunma.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;
@Slf4j
@Service
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SysUserMapper sysUserMapper;
    //发送人
    private String from="2636822826@qq.com";
    //标题
    private String title="寻码科技";
    public void seedMessage(String to,String context){
        
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
            log.error("发送邮件给{}失败",to);
            e.printStackTrace();
        }
    }

    public void sendAllMessage() {
        //获取所有用户
        List<SysUser> sysUsers = sysUserMapper.selectUserList(new SysUser());
        for (SysUser sysUser : sysUsers) {
            if (sysUser.getEmail()==null||sysUser.getEmail().equals("")){
                break;
            }
            String context="尊敬的客户、管理员您好！" +
                    "平台已收到新订单........." +
                    "管理平台链接为:http://192.168.1.7/login?redirect=%2Findex";
            seedMessage(sysUser.getEmail(),context);
            log.info("发送邮件给{}成功",sysUser.getEmail());
        }
    }
}
