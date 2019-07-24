import com.winston.spring.banner.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
* spring boot启动类
* @author Winston.Wang
* @date 2019/7/21
* @version 1.0
**/
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.winston")
public class Application {
    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBanner(new MyBanner());
        springApplication.run(args);
    }
}
