package goorm.tricount.domain.dto.response.user;

import goorm.tricount.domain.entity.user.UserType;
import goorm.tricount.domain.entity.user.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {
    private Long usersId;
    private String nickname;
    private LocalDateTime createAt;
    private UserType type;

    public UserDetailDto(Users user) {
        this.usersId = user.getUsersId();
        this.nickname = user.getNickname();
        this.createAt = user.getCreateAt();
        this.type = user.getType();
    }
}
