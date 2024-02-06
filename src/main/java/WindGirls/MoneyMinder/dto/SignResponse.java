package WindGirls.MoneyMinder.dto;

import WindGirls.MoneyMinder.domain.Authority;
import WindGirls.MoneyMinder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {

    private Long id;

    private String account;

    private String name;

    private String email;

    private List<Authority> roles = new ArrayList<>();

    private String token;

    public SignResponse(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
