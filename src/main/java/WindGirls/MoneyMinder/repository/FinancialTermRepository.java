package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.FinancialTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FinancialTermRepository extends JpaRepository<FinancialTerm, String> {
    Optional<FinancialTerm> findByTerm(String term);

   @Query("SELECT id FROM FinancialTerm")
    List<Long> findAllIds();
    // 추가적인 쿼리 메서드가 필요하다면 여기에 추가할 수 있음

}
