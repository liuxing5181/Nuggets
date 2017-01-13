package com.nuggets.events;

/**
 *
 * 1、定义事件：
 */
public class ItemSelectedEvent {

    public final String title;

    public ItemSelectedEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
