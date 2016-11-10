package com.tnt.fund.analyse.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanManager {
    private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static Object getBean(String beanId) {
        return context.getBean(beanId);
    }

}
