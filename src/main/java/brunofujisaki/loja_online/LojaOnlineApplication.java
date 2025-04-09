package brunofujisaki.loja_online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LojaOnlineApplication {
	public static void main(String[] args) {
		SpringApplication.run(LojaOnlineApplication.class, args);
	}
}

