package com.xunma.common.enums;

public enum TimeType {
    MILLISECOND(1, "毫秒"),
    SECOND(1000, "秒"),
    MINUTE(60 * SECOND.getMillisecond(), "分钟"),
    HOUR(60 * MINUTE.getMillisecond(), "小时"),
    DAY(24 * HOUR.getMillisecond(), "天");

    private final Integer millisecond;
    private final String info;

    TimeType(Integer millisecond, String info) {
        this.millisecond = millisecond;
        this.info = info;
    }

    public Integer getMillisecond() {
        return millisecond;
    }

    public String getInfo() {
        return info;
    }
}
