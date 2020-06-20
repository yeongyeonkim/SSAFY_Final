package restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AtAnyCostApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AtAnyCostApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 모든 요청에 대해서
				.allowedOrigins("*"); // 허용할 오리진들
	}
}