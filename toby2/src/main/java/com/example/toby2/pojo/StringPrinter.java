package com.example.toby2.pojo;

public class StringPrinter implements Printer {
    private StringBuilder sb = new StringBuilder();

    @Override
    public void print(String message) {
        sb.append(message);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
