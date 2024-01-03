package com.xunma.system.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xunma.common.constant.CommonConstants;
import com.xunma.common.enums.TimeType;
import com.xunma.common.rabbitmq.RabbitmqUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class RabbitmqService {
    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;


    public void sendDeLayMessage(String code, String message, Integer millisecond, TimeType timeType){
        try {
            Map<String,String> map = new HashMap<>();
            map.put(CommonConstants.RABBITMQ_CODE,code);
            map.put(CommonConstants.RABBITMQ_MESSAGE,message);
            RabbitmqUtils.sendDelayMessage(rabbitTemplate,millisecond*timeType.getMillisecond(),objectMapper.writeValueAsString(map),System.currentTimeMillis());
            log.info("发送延迟队列消息成功：消息内容："+map);
        }catch (Exception e){
            log.error("发送延迟队列消息失败：{}",e.getMessage());
        }
    }
}
