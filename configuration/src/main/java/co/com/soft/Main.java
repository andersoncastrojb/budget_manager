package co.com.soft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "co.com.soft")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}