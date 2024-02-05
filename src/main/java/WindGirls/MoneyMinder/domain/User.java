package WindGirls.MoneyMinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; //유저번호

    @Column(name = "account", nullable = false)
    private String account; // 로그인 ID

    @Column(name = "password", nullable = false)
    private String password;    // 로그인 비밀번호

    @Column(name = "name", nullable = false)
    private String name;    // 유저실명

    @Column(name = "email", nullable = false)
    private String email;       // 이메일

    @OneToMany(mappedBy = "user1")
    private List<ChatRoom> chatRooms1 = new ArrayList<>();

    @OneToMany(mappedBy = "user2")
    private List<ChatRoom> chatRooms2 = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> role) {
        this.roles = role;
        role.forEach(o -> o.setUser(this));
    }

}
