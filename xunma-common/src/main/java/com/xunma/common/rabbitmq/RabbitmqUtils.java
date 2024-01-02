package com.xunma.common.rabbitmq;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
public class RabbitmqUtils {

    /**
     * 发送延迟消息
     * @param rabbitTemplate rabbitTemplate
     * @param millisecond 延迟毫秒
     * @param messageContent 发送字符串
     * @param busiId 业务主键id
     */
    public static void sendDelayMessage(RabbitTemplate rabbitTemplate, Integer millisecond, Object messageContent, Long busiId) {
        CorrelationData correlationData = new CorrelationData(busiId.toString() + System.currentTimeMillis());
        rabbitTemplate.convertAndSend("updateAmount", "", messageContent,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message.getMessageProperties().setDelay(millisecond);
                    return message;
                }, correlationData);
    }
}

