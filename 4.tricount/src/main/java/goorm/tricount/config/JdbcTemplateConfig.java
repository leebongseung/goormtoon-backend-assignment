package goorm.tricount.config;

import goorm.tricount.repository.SettlementRepository;
import goorm.tricount.repository.UserRepository;
import goorm.tricount.repository.jdbctemplate.jdbcTemplateSettlementRepository;
import goorm.tricount.repository.jdbctemplate.jdbcTemplateUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig {
    private final DataSource dataSource;

    @Bean
    public UserRepository userRepository() {
        return new jdbcTemplateUserRepository(dataSource);
    }

    @Bean
    public SettlementRepository settlementRepository() {
        return new jdbcTemplateSettlementRepository(dataSource);
    }
}
