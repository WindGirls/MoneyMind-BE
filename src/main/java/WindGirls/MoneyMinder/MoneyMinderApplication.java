package WindGirls.MoneyMinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.springframework.web.filter.CorsFilter"})
public class MoneyMinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyMinderApplication.class, args);
	}

}
