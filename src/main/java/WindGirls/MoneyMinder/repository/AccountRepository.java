package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserIdAndTimesBetween(Long userId, Timestamp startOfMonth, Timestamp endOfMonth);
}
