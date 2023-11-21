package goorm.tricount.domain.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class UserSingUpDto {
    @NotBlank(message = "{NotBlank.id}")
    @Size(min = 8, max = 20, message = "{Size.id}")
    final private String id;

    @NotBlank(message = "{NotBlank.password}")
    @Size(min = 8, max = 20, message = "{Size.password}")
    final private String password;

    @NotBlank(message = "{NotBlank.nickName}")
    @Size(min = 2, max = 20, message = "{Size.nickName}")
    final private String nickName;
}
