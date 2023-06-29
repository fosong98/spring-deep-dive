package com.example.toby2.pojo;

import org.springframework.beans.factory.config.BeanDefinition;

public class Missile implements Weapon {
    @Override
    public String use() {
        return "fires a missile!";
    }
}
