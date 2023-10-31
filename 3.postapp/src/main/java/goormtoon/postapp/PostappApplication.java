package goormtoon.postapp;

import goormtoon.postapp.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class PostappApplication {
	public static void main(String[] args) {
		SpringApplication.run(PostappApplication.class, args);
	}
}
