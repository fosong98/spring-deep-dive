package com.example.toby2boot;

import jakarta.servlet.ServletContext;
import org.apache.catalina.core.ApplicationContextFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

@RestController
public class Controller {
    @Autowired
    ApplicationContext ac;
    @Autowired
    DispatcherServlet ds;
    @RequestMapping
    public String test() {
        return "Controller.test";
    }
}
