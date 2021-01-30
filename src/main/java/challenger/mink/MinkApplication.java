package challenger.mink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MinkApplication {

  public static void main(String[] args) {
    SpringApplication.run(MinkApplication.class, args);
    System.out.println("what does the mink say?");

  }
}