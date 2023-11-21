package goorm.tricount.domain.dto.request.user;

import goorm.tricount.domain.entity.user.Users;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class UserLoginDto {
    @NotBlank(message = "{NotBlank.id}")
    @Size(min = 8, max = 20, message = "{Size.id}")
    private String id;

    @NotBlank(message = "{NotBlank.password}")
    @Size(min = 8, max = 20, message = "{Size.password}")
    private String password;

    public UserLoginDto(Users user) {
        this.id = user.getId();
        this.password = user.getPassword();
    }
}
