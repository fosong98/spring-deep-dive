package com.example.toby2boot;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.core.ApplicationContextFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContext;

@RestController
@Lazy(true)
public class Controller {
    @Autowired
    ApplicationContext ac;
    @Autowired
    DispatcherServlet ds;

    long[] oom;

    public Controller() {
        // lazy 로딩 시 런타임에 oom이 발생한다.
//        oom = new long[Integer.MAX_VALUE];
    }

    @RequestMapping
    public String test() {
        ApplicationContext parent = ac.getParent();
        Object controller = parent.getBean("hello");
        return "Controller.test";
    }

}
