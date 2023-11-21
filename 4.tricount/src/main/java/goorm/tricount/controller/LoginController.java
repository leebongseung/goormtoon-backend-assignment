package goorm.tricount.controller;

import goorm.tricount.api.ErrorCode;
import goorm.tricount.api.response.ApiResponse;
import goorm.tricount.domain.dto.request.user.UserLoginDto;
import goorm.tricount.domain.dto.request.user.UserSingUpDto;
import goorm.tricount.exception.users.UserUnprocessableEntityException;
import goorm.tricount.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    final private LoginService loginService;

    @PostMapping("/signup")
    public ApiResponse<String> signUp(@RequestBody @Valid UserSingUpDto userSingUpDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserUnprocessableEntityException();
        }
        loginService.signup(userSingUpDto);
        return new ApiResponse<>(ErrorCode.OK, "회원가입 완료");
    }

    @PostMapping("/login")
    public ApiResponse<UserLoginDto> loginUser(@RequestBody @Valid UserLoginDto userLoginDto, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            throw new UserUnprocessableEntityException();
        }
        UserLoginDto login = loginService.login(userLoginDto, request);
        return new ApiResponse<>(ErrorCode.OK, login);
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String message;
        session.invalidate(); //세션 만료 시키기
        message = "로그아웃 완료되었습니다.";

        return new ApiResponse<>(ErrorCode.OK, message);
    }


}
