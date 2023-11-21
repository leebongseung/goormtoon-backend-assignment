package goorm.tricount.repository;

import goorm.tricount.domain.dto.request.user.UserLoginDto;
import goorm.tricount.domain.entity.user.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    public void saveUser(Users user);
    public Optional<Users> findByStringId(String id);

    public Optional<Users> login(UserLoginDto userLoginDto);


}
