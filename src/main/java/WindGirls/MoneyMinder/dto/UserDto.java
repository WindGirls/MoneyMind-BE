package WindGirls.MoneyMinder.dto;

import WindGirls.MoneyMinder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;


public class UserDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String account;
        private String name;
        private String email;

        public static Response of(User user) {
            return Response.builder()
                    .id(user.getId())
                    .account(user.getAccount())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }
    }

}
