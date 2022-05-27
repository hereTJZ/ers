package com.example.ers.entity;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
public class DelayTask<T extends Runnable> implements Delayed {
    // 延时实验
    private Experiment experiment;
    // 多线程任务
    private final T task;
    // 执行时间
    private long executeTime;

    // 延时时间(精度为毫秒)
    @Override
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(executeTime - System.currentTimeMillis(), timeUnit);
    }

    // 队列排序规则
    @Override
    public int compareTo(Delayed delayed) {
        return (this.getDelay(TimeUnit.MILLISECONDS) - delayed.getDelay(TimeUnit.MILLISECONDS) >= 0) ? 1 : -1;
    }

    @Override
    public String toString() {
        return "DelayTask{" +
                "experiment=" + experiment +
                ", executeTime=" + executeTime +
                '}';
    }
}
