package com.example.toby2boot;

import org.springframework.beans.BeansException;
import org.springframework.context.support.StaticApplicationContext;

public class TestApplicationContext extends StaticApplicationContext {
    public TestApplicationContext() throws BeansException {
        this.registerSingleton("hello", Hello.class);
    }

    static class Hello {
        public String name = "Jaedoo";
    }
}
