package com.xunma.common.constant;

public class CommonConstants {
    //rabbitmq code
    public static final String RABBITMQ_CODE = "code";
    //rabbitmq message
    public static final String RABBITMQ_MESSAGE = "message";
    //发送邮件任务
    public static final String SEND_EMAIL_TASK = "0";
    //延迟更改订单状态任务
    public static final String DELAY_CHANGE_ORDER_STATUS_TASK = "1";
    //秒
    public static final Integer SECOND = 1000;
    //分钟
    public static final Integer MINUTE = 60 * SECOND;
    //小时
    public static final Integer HOUR = 60 * MINUTE;
    //天
    public static final Integer DAY = 24 * HOUR;
    //订单超时时间
    public static final Integer ORDER_TIMEOUT = 30 * MINUTE;
}
