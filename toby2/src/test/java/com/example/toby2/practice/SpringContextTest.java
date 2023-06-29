package com.example.toby2.practice;

import com.example.toby2.pojo.Hello;
import com.example.toby2.pojo.Missile;
import com.example.toby2.pojo.Robot;
import com.example.toby2.pojo.StringPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

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

        BeanDefinition mazingerZDef = new RootBeanDefinition(Robot.class);
        mazingerZDef.getPropertyValues().add("name", "MAZINGER Z");
        ac.registerBeanDefinition("mazingerZ", mazingerZDef);

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

    @Test
    public void 로봇테스트() {
        StaticApplicationContext context = new StaticApplicationContext();
        context.registerSingleton("TaekwonV", Robot.class);

        Robot taekwonV = context.getBean("TaekwonV", Robot.class);
        Assertions.assertNotNull(taekwonV);

        context.registerSingleton("missile", Missile.class);

        BeanDefinition mazingerZDef = new RootBeanDefinition(Robot.class);
        mazingerZDef.getPropertyValues().add("name", "MAZINGER Z");
        mazingerZDef.getPropertyValues().add("weapon",
                new RuntimeBeanReference("missile"));

        context.registerBeanDefinition("mazingerZ", mazingerZDef);

        Robot mazingerZ = context.getBean("mazingerZ", Robot.class);

        Assertions.assertNotNull(mazingerZ);
        Assertions.assertNotEquals(taekwonV, mazingerZ);
        Assertions.assertEquals("MAZINGER Z fires a missile!", mazingerZ.attack());
    }

    @Test
    public void 일반적인_컨텍스트_테스트1() {
        GenericApplicationContext gac = new GenericApplicationContext();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(gac);

        reader.loadBeanDefinitions("test-context.xml");
        gac.refresh();

        Robot taekwonV = gac.getBean("taekwonV", Robot.class);

        assertNotNull(taekwonV);
    }

    @Test
    public void 일반적인_컨텍스트_테스트2() {
        GenericApplicationContext gac = new GenericApplicationContext();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(gac);

        reader.loadBeanDefinitions("test-context.properties");
        gac.refresh();

        Robot taekwonV = gac.getBean("taekwonV", Robot.class);

        assertNotNull(taekwonV);
    }

    @Test
    public void 컨텍스트_계층구조_테스트() {
        StaticApplicationContext ac1 = new StaticApplicationContext();
        StaticApplicationContext ac2 = new StaticApplicationContext();
        StaticApplicationContext ac3 = new StaticApplicationContext();

        ac1.registerSingleton("robot1", Robot.class);
        ac2.registerSingleton("robot2", Robot.class);
        ac3.registerSingleton("robot3", Robot.class);

        /** 계층구조
         *     1
         *    /\
         *   2  3
         */
        ac2.setParent(ac1);
        ac3.setParent(ac1);

        Robot robot1 = ac2.getBean("robot1", Robot.class);
        Robot robot2 = ac3.getBean("robot1", Robot.class);

        assertEquals(robot1, robot2);
        assertThrows(BeansException.class, ()->ac2.getBean("robot3"));
    }
}
