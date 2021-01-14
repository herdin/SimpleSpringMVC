package com.harm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {
    public enum TEST {
        TEST1("11"),
        TEST2("22"),
        ;
        private String value;
        TEST(String value) { this.value = value;}
        public static TEST convert(String value) {
            TEST converted = null;
            for(TEST t : TEST.values()) {
                if(t.value.equals(value)) {
                    converted = t;
                    break;
                }
            }
            return converted;
        }
    }
    public static void main(String[] args) {
//        SpringApplication.run(MainApp.class);
        TEST test = TEST.convert("23");
        switch(test) {
            case TEST1:
                System.out.println("this is test1 " + test.value);
                break;
            case TEST2:
                System.out.println("this is test2 " + test.value);
                break;
            default:
                System.out.println("this is default");
                break;
        }
    }
}
