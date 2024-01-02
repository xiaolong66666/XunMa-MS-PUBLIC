package com.xunma.common.rabbitmq;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.xunma.common.constant.CommonConstants;
import com.xunma.common.core.domain.entity.SysUser;
import com.xunma.common.mail.MailServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
@RabbitListener(queues = "LAZY_QUEUE")
public class RabbitmqListener {

    private final ObjectMapper objectMapper;
    @Autowired
    private MailServiceUtil mailServiceUtil;


    @RabbitHandler
    public void onCustomBookingMessage(@Payload String message, Channel channel, @Headers Map<String, Object> headers) {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            //确定消息类型
            Map map = objectMapper.readValue(message, Map.class);
            String code=(String)map.get(CommonConstants.RABBITMQ_CODE);
            operateMessage(code,map);
            log.info("接收消息成功，消息内容："+map);
            ack(channel,deliveryTag,"");
        } catch (JsonProcessingException e) {
            nack(channel,deliveryTag,"");
        }
    }

    private void operateMessage(String code, Map map) {
        String jsonStr= (String) map.get(CommonConstants.RABBITMQ_MESSAGE);
        if (code.equals(CommonConstants.SEND_EMAIL_TASK)){
            //发送邮件
            List<SysUser> users = JSONUtil.toList(jsonStr, SysUser.class);
            mailServiceUtil.sendAllMessage(users);
        }

    }


    /**
     * 确认消息成功
     */
    public void ack(Channel channel, long deliveryTag, String info) {
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            log.error(info + ":消息应答出错", e);
        }
    }

    /**
     * 确认消息失败
     */
    public void nack(Channel channel, long deliveryTag, String info) {
        try {
            channel.basicNack(deliveryTag, false, true);
        } catch (IOException e) {
            log.error(info + ":消息拒绝出错", e);
        }
    }
}
