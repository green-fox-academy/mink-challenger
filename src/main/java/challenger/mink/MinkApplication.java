package challenger.mink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MinkApplication {

  public static void main(String[] args) {
    SpringApplication.run(MinkApplication.class, args);
    System.out.println("what does the mink say?");

  }
}