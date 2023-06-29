package com.example.toby2.practice;

import com.example.toby2.pojo.Hello;
import com.example.toby2.pojo.StringPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class SpringContextTest {
    StaticApplicationContext ac;

    @BeforeEach
    public void setUp() {
        ac = new StaticApplicationContext();
    }

    @Test
    public void 기본동작() {
        ac.registerSingleton("hello1", Hello.class);

        assertNotNull(ac.getBean("hello1", Hello.class));
    }

    @Test
    public void BeanDefinition_사용() {
        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().add("name", "Spring");
        ac.registerSingleton("hello1", Hello.class);
        ac.registerBeanDefinition("hello2", helloDef);

        Hello hello1 = ac.getBean("hello1", Hello.class);
        Hello hello2 = ac.getBean("hello2", Hello.class);
        assertNotNull(hello2);
        assertNotEquals(hello1, hello2);

        assertEquals(2, ac.getBeanDefinitionCount());
    }

    @Test
    public void DI_테스트_BeanReference_이용() {
        ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().add("name", "Spring");
        helloDef.getPropertyValues().add("printer", new RuntimeBeanReference("printer"));

        ac.registerBeanDefinition("hello", helloDef);

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        assertEquals("Hello Spring", ac.getBean("printer").toString());
    }
}
