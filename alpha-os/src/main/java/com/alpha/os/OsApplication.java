package com.alpha.os;

import com.alpha.os.cpu.Sync4cpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.*;

/**
 * Created by qiang on 2018/3/28.
 */
@SpringBootApplication
public class OsApplication implements CommandLineRunner {
    @Autowired
    private final static ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    public static void main(String[] args) {
        SpringApplication.run(OsApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(strings);

        System.out.println(this.getClass().getPackage().getName());

//        sync4cpu.compete();
//        service.scheduleAtFixedRate(() -> System.out.println(sync4cpu.getMap() + "A"),0,2000, TimeUnit.MILLISECONDS);

    }
}

