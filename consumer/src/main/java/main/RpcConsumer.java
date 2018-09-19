package main;

import inter.IBaseService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RpcConsumer {

    public static void main(String[] args) {
        //测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("consumer start");
        IBaseService demoService = (IBaseService) context.getBean("permissionService");
        System.out.println("consumer");
        System.out.println(demoService.testService("Hello Word"));
    }
}
