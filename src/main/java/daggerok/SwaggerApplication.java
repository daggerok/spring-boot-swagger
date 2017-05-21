package daggerok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static java.util.Arrays.asList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@EnableSwagger2
@SpringBootApplication
public class SwaggerApplication {

  @Bean
  public Docket workforceManagementApi() {

    return new Docket(SWAGGER_2).select()
                                .apis(withClassAnnotation(RestController.class))
                                .paths(or(asList(
                                    regex("^/v.*"), // /v*
                                    regex("/.*(some|other)-.*"), // /*some-* or /*other-*
                                    regex("/.*-resource.*"), // /*-resource*
                                    regex("/.*-resource(.+|$)") // /*-resource/x or /*-resource
                                )))
                                .build()
                                .tags(new Tag("v1", "Some API for version v1"))
                                .apiInfo(new ApiInfoBuilder().description("Some API")
                                                             .build());
  }

  public static void main(String[] args) {
    SpringApplication.run(SwaggerApplication.class, args);
  }
}
