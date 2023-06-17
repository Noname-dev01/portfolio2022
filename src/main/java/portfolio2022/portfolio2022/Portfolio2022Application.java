package portfolio2022.portfolio2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Portfolio2022Application {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application-oauth.properties,"
			+ "classpath:application.yml,"
			+ "classpath:aws.yml";

	public static void main(String[] args){
		new SpringApplicationBuilder(Portfolio2022Application.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
//	public static void main(String[] args) {
//		SpringApplication.run(Portfolio2022Application.class, args);
//	}

}
