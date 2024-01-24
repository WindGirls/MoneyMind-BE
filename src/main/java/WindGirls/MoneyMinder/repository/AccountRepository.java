package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // 특별한 메소드가 필요하면 추가 가능
}
