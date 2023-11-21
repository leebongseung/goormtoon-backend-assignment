package goorm.tricount;

import goorm.tricount.repository.jdbctemplate.jdbcTemplateUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(jdbcTemplateUserRepository.class)
@SpringBootApplication
public class TricountApplication {

	public static void main(String[] args) {
		SpringApplication.run(TricountApplication.class, args);
	}

}
