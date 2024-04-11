package WindGirls.MoneyMinder.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "financial_terms")
public class FinancialTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "term", nullable = false)
    private String term;

    @Column(name = "definition", nullable = false, columnDefinition = "TEXT")
    private String definition;

    public FinancialTerm(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    // 필요한 생성자, getter, setter, 기타 메서드 추가
}
