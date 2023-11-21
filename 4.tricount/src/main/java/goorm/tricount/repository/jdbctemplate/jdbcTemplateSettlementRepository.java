package goorm.tricount.repository.jdbctemplate;

import goorm.tricount.domain.dto.request.settlement.CreateSettlementDto;
import goorm.tricount.domain.dto.response.settlement.SettlementDetailDto;
import goorm.tricount.domain.dto.response.settlement.SettlementDto;
import goorm.tricount.domain.dto.response.settlement.UsersHasSettlementResponseDto;
import goorm.tricount.domain.dto.response.user.LoginUser;
import goorm.tricount.domain.entity.settlement.Settlement;
import goorm.tricount.exception.settlement.SettlementHttpNotFoundException;
import goorm.tricount.exception.settlement.UsersHasSettlementHttpNotFoundException;
import goorm.tricount.exception.users.UserHttpNotFoundException;
import goorm.tricount.repository.SettlementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class jdbcTemplateSettlementRepository implements SettlementRepository {
    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsertSettlement;
    private final SimpleJdbcInsert jdbcInsertUserHasSettlement;

    public jdbcTemplateSettlementRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsertSettlement = new SimpleJdbcInsert(dataSource)
                .withTableName("SETTLEMENT")
                .usingGeneratedKeyColumns("SETTLEMENT_ID");
        this.jdbcInsertUserHasSettlement = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS_HAS_SETTLEMENT")
                .usingGeneratedKeyColumns("users_has_settlement_id");
    }

//    @Override
//    public SettlementDetailDto createSettlement(Long userId,CreateSettlementDto settlementDto) {
//        log.info("jdbcTemplateSettlementRepository.createSettlement");
//        // 유저 찾기
//        LoginUser loginUser = findByLoginUsersId(userId).orElseThrow(UserHttpNotFoundException::new);
//
//        // Settlment 생성하기
//        SettlementDto settlement = jdbcInsertSettlement(settlementDto);
//
//        // Settlment와 User매핑하는 n대 n 테이블 도 객체값 짚어넣기.
//        Long UsersHasSettlementId = jdbcInsertUsersHasSettlement(loginUser, settlement);
//
//        // 출력을 위한 포맷
//        UsersHasSettlementResponseDto usersHasSettlementResponseDto = findByUsersHasSettlementId(UsersHasSettlementId).orElseThrow(UsersHasSettlementHttpNotFoundException::new);
//
//        log.info("usersHasSettlementResponseDto ={}, {}", usersHasSettlementResponseDto.getSettlementId(), usersHasSettlementResponseDto.getUsersId());
//
//        return new SettlementDetailDto(settlement, usersHasSettlementResponseDto);
//    }
//
//    private SettlementDto jdbcInsertSettlement(CreateSettlementDto settlementDto) {
//        Settlement newSettlement = new Settlement(settlementDto);
//        SqlParameterSource param = new BeanPropertySqlParameterSource(newSettlement);
//        Long SettlementId = (Long) jdbcInsertSettlement.executeAndReturnKey(param);
//
//        SettlementDto settlement = findBySettlementId((Long) SettlementId)
//                .orElseThrow(SettlementHttpNotFoundException::new);
//        return settlement;
//    }
//
//    private Long jdbcInsertUsersHasSettlement(LoginUser loginUser, SettlementDto settlement) {
//        UsersHasSettlementResponseDto usersHasSettlement = new UsersHasSettlementResponseDto(loginUser, settlement);
//        SqlParameterSource param2 = new BeanPropertySqlParameterSource(usersHasSettlement);
//        Long UsersHasSettlementId = (Long) jdbcInsertUserHasSettlement.executeAndReturnKey(param2);
//        return UsersHasSettlementId;
//    }
//
//    @Override
//    public Optional<SettlementDto> findBySettlementId(Long settlementId) {
//        String sql = "select * " +
//                "from SETTLEMENT " +
//                "where SETTLEMENT_ID  = :id";
//        try{
//            Map<String, Long> param2 = Map.of("id", settlementId);
//            SettlementDto settlementDto = template.queryForObject(sql, param2, SettlementDtoRowMapper());
//            return Optional.of(settlementDto);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public Optional<LoginUser> findByLoginUsersId(Long id){
//        String sql = "select * " +
//                "from users " +
//                "where users_id = :id";
//        try{
//            Map<String, Long> param2 = Map.of("id", id);
//            LoginUser loginUser = template.queryForObject(sql, param2, LoginUserRowMapper());
//            return Optional.of(loginUser);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public Optional<UsersHasSettlementResponseDto> findByUsersHasSettlementId(Long id){
//        String sql = "select USERS_HAS_SETTLEMENT_ID ,SETTLEMENT_ID ,USERS_ID " +
//                "from USERS_HAS_SETTLEMENT " +
//                "where USERS_HAS_SETTLEMENT_ID = :id";
//        try{
//            Map<String, Long> param2 = Map.of("id", id);
//            UsersHasSettlementResponseDto usersHasSettlementResponseDto = template.queryForObject(sql, param2, UsersHasSettlementResponseDtoRowMapper());
//            return Optional.of(usersHasSettlementResponseDto);
//        } catch (EmptyResultDataAccessException e) {
//            return Optional.empty();
//        }
//    }
//
//    @Override
//    public List<UsersHasSettlementResponseDto> findUserHasSettlementBySettlementId(Long settlementId) {
//        String sql = "select USERS_HAS_SETTLEMENT_ID ,SETTLEMENT_ID ,USERS_ID " +
//                "from USERS_HAS_SETTLEMENT " +
//                "where SETTLEMENT_ID = :id";
//        Map<String, Long> param2 = Map.of("id", settlementId);
//        return template.query(sql, param2, UsersHasSettlementResponseDtoRowMapper());
//    }
//
//    private RowMapper<SettlementDto> SettlementDtoRowMapper() {
//        return BeanPropertyRowMapper.newInstance(SettlementDto.class);
//    }
//
//    private RowMapper<UsersHasSettlementResponseDto> UsersHasSettlementResponseDtoRowMapper() {
//        return BeanPropertyRowMapper.newInstance(UsersHasSettlementResponseDto.class);
//    }
//
//    private RowMapper<LoginUser> LoginUserRowMapper() {
//        return BeanPropertyRowMapper.newInstance(LoginUser.class);
//    }
}
