package WindGirls.MoneyMinder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // 해당 경로에 대해서만 CORS 허용
                .allowedOrigins("http://localhost:8081")  // 프론트엔드 서버의 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
