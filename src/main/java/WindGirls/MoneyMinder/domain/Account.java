package WindGirls.MoneyMinder.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id") // 컬럼 이름을 명시
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //상대 엔티티에 테이블에 FK 칼럼이 있음을
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "deposit", nullable = false)
    private int deposit;

    @Column(name = "withdrawal", nullable = false)
    private int withdrawal;


    @Column(name = "place", nullable = false)
    private int place;

    @Column(name = "times", nullable = false)
    private Date times;


    @Column(name = "balance", nullable = false)
    private int balance;




}