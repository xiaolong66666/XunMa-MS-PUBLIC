package com.xunma.system.service.impl;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.xunma.common.constant.CommonConstants;
import com.xunma.system.mapper.XmOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
@RabbitListener(queues = "LAZY_QUEUE")
public class RabbitmqOperateService {

    private final ObjectMapper objectMapper;
    @Autowired
    private SendMailService sendMailService;
    @Autowired
    private XmOrderMapper xmOrderMapper;


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
    //rabbitmq异步处理消息
    private void operateMessage(String code, Map map) {
        String jsonStr= (String) map.get(CommonConstants.RABBITMQ_MESSAGE);
        if (code.equals(CommonConstants.SEND_EMAIL_TASK)){
            //向管理员和客户发送邮件
            sendMailService.sendAllMessage();
        }
        if (code.equals(CommonConstants.DELAY_CHANGE_ORDER_STATUS_TASK)){
            //修改订单状态
            int i = xmOrderMapper.updateXmOrderStatus(Long.valueOf(jsonStr));
            if (i==0){
                log.error("修改订单状态失败，订单id："+jsonStr);
            }else {
                log.info("修改订单状态成功，订单id："+jsonStr);
            }
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
