package goorm.tricount.repository.jdbctemplate;

import goorm.tricount.domain.dto.request.user.UserLoginDto;
import goorm.tricount.domain.entity.user.Users;
import goorm.tricount.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class jdbcTemplateUserRepository implements UserRepository {
    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public jdbcTemplateUserRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("USERS_ID");
    }


    @Override
    public void saveUser(Users user) {
        log.info("jdbcTemplateUserRepository.save");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);
        jdbcInsert.executeAndReturnKey(param);
    }

    @Override
    public Optional<Users> findByStringId(String id) {
        log.info("jdbcTemplateUserRepository.findById");
        String sql = "select id " +
                "from users " +
                "where id = :id";
        try{
            Map<String, String> param = Map.of("id", id);
            Users user = template.queryForObject(sql, param, UserRowMapper());
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Users> login(UserLoginDto userLoginDto) {
        log.info("jdbcTemplateUserRepository.login");
        String sql = "select users_id, id, password,  nickname, create_at, update_at, type " +
                "from USERS " +
                "where id = :id and password = :password";

        try{
//            log.info("UserLoginDto.id={}", userLoginDto.getId());
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("id", userLoginDto.getId())
                    .addValue("password", userLoginDto.getPassword());
            Users user = template.queryForObject(sql, param, UserRowMapper());
            log.info("userid={}", user.getUsersId());
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Users> UserRowMapper() {
        return BeanPropertyRowMapper.newInstance(Users.class);
    }
}
