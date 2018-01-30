package torres.javier.api.payment.fx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackages = {"io.swagger", "torres.javier.api.payment.fx"})
@SpringBootApplication
public class Application {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(Application.class, args);
  }
}
