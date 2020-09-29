package com.example.eventbustest.event;

/**
 * @author : Created by Wyy
 * @time : 2020/9/18
 */
public class TestMessageEvent {
    private String TestMessage;

    public String getTestMessage() {
        return TestMessage;
    }

    public TestMessageEvent(String testMessage) {
        TestMessage = testMessage;
    }
}
