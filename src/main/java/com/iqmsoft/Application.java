package com.iqmsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        MyService service = ctx.getBean(MyService.class);

        new Thread(() -> {
            String prevResult = "";
            int count = 0;
            int step = 10;

            while(true) {
                String result = service.getData("sample");
                if (!result.equals(prevResult)) {
                    prevResult = result;
                    count = 0;
                }

                System.out.println(count + " seconds since created. \t Result: " + result);

                try {Thread.sleep(step * 1000);} catch(Exception e){}
                count += step;
            }

        }).run();

    }
}