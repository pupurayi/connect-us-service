package zw.co.connectus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Bootstrap {

	public static void main(String[] args) {

		SpringApplication.run(Bootstrap.class, args);
	}

}
