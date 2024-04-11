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
        registry.addMapping("/**")  // 해당 경로에 대해서만 CORS 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedOrigins("http://localhost:3000","http://localhost:8082","http://localhost:8081","http://localhost:8084",
                        "exp://192.168.219.100:8082","exp://192.168.219.100:8081","exp://192.168.219.100:8083","exp://192.168.219.100:8084")  // 프론트엔드 서버의 주소
                .allowCredentials(true); // 쿠키 인증 요청 허용
//        registry.addMapping("/**")
//                .allowedMethods("*")
//                .allowedOriginPatterns("*")
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//                .allowCredentials(true);
    }
}
