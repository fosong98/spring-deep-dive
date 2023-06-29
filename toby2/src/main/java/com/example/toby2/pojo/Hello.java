package com.example.toby2.pojo;

import lombok.Setter;

@Setter
public class Hello {
    String name;
    Printer printer;

    public String sayHello() {
        return "Hello " + name;
    }

    public void print() {
        this.printer.print(sayHello());
    }
}
