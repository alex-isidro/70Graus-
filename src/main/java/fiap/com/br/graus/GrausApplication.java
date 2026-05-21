package fiap.com.br.graus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GrausApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrausApplication.class, args);
	}
}
