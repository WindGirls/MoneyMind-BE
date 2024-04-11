package WindGirls.MoneyMinder.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id; //카테고리번호

    private String name;//카테고리이름

    @OneToMany(mappedBy = "category")
    private List<Account> accounts = new ArrayList<>();
}

