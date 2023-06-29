package com.example.toby2;

import com.example.toby2.pojo.Hello;
import org.springframework.context.support.StaticApplicationContext;

public class ContextTest {
    public static void main(String[] args) {
        StaticApplicationContext ac = new StaticApplicationContext();

        ac.registerSingleton("hello1", Hello.class);
    }
}
