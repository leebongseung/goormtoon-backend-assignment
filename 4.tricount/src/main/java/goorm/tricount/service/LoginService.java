package goorm.tricount.service;

import goorm.tricount.domain.dto.request.user.UserLoginDto;
import goorm.tricount.domain.dto.request.user.UserSingUpDto;
import goorm.tricount.domain.entity.user.Users;
import goorm.tricount.exception.users.UserConflitException;
import goorm.tricount.exception.users.UserUnauthorizedException;
import goorm.tricount.repository.JpaUserRepository;
import goorm.tricount.repository.UserRepository;
import goorm.tricount.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

//    final private UserRepository userRepository;
    final private JpaUserRepository userRepository;

    @Transactional
    public void signup(UserSingUpDto singUpDto) {
        Users user = new Users(singUpDto);
        Optional<Users> alreadyUserOptional = userRepository.findByStringId(user.getId());
        if(alreadyUserOptional.isPresent()) {
            throw new UserConflitException();
        }

        userRepository.save(user);
    }

    public UserLoginDto login(UserLoginDto userLoginDto, HttpServletRequest request) {
        Users user = userRepository.login(userLoginDto.getId(), userLoginDto.getPassword()).orElseThrow(UserUnauthorizedException::new);

        // 세션 구현하기
        HttpSession session = request.getSession(true);

        // 세션에 로그인 회원 정보 보관하기
        session.setAttribute(SessionConst.LOGIN_MEMBER, user.getUsersId());

        return userLoginDto;
    }
}
