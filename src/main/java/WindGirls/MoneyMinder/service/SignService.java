package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.domain.Authority;
import WindGirls.MoneyMinder.domain.User;
import WindGirls.MoneyMinder.dto.SignRequest;
import WindGirls.MoneyMinder.dto.SignResponse;
import WindGirls.MoneyMinder.jwt.JwtProvider;
import WindGirls.MoneyMinder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SignResponse login(SignRequest request) throws Exception {
        User user = userRepository.findByAccount(request.getAccount()).orElseThrow(() ->
                new BadCredentialsException("잘못된 계정정보입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        return SignResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .token(jwtProvider.createToken(user.getAccount(), user.getRoles()))
                .build();

    }

    public boolean register(SignRequest request) throws Exception {
        try {
            User user = User.builder()
                    .account(request.getAccount())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .email(request.getEmail())
                    .build();

            user.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));

            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    public SignResponse getUser(String account) throws Exception {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return new SignResponse(user);
    }

}
