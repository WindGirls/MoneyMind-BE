package WindGirls.MoneyMinder.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat_bot_message")
public class ChatBot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //상대 엔티티에 테이블에 FK 칼럼이 있음을
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
//    private User sender;

    @Column(nullable = false)
    private String content;



    @Column(name = "c_times")
    private LocalDateTime CTimes;

    public void setContent(String content) {
    }

    public void setCSenderId(long cSenderId) {
    }

    public void setCTimes(Timestamp cTimes) {
    }

    public void setUser(User user) {
        this.user = user;
    }
}

