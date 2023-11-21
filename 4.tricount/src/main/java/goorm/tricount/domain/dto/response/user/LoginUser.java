package goorm.tricount.domain.dto.response.user;

import goorm.tricount.api.ErrorCode;
import goorm.tricount.domain.entity.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private Long usersId;
    private String id;
    private String password;
    private String nickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private UserType type;
}
