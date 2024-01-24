package WindGirls.MoneyMinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; //유저번호

    @Column(length = 20, nullable = false)
    private String name; //유저이름

    @Column(nullable = false)
    private Long age; //유저나이

    @Column(length = 50, nullable = false)
    private String job; //유저직업

    @Column(nullable = false)
    private Long token; //토큰

    @OneToMany(mappedBy = "user1")
    private List<ChatRoom> chatRooms1 = new ArrayList<>();

    @OneToMany(mappedBy = "user2")
    private List<ChatRoom> chatRooms2 = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<>();


}
