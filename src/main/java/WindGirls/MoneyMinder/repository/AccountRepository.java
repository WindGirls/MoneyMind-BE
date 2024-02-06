package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // 다음과 같이 쿼리를 작성하여 최근 잔액을 가져오는 메서드를 정의합니다.
    @Query("SELECT a.balance FROM Account a ORDER BY a.times DESC LIMIT 1")
    int findLatestBalance();

    List<Account> findAllByTimesContaining(Date date);


    List<Account> findByUserId(Long user_id);

    List<Account> findAllByTimesAndUserId(Date date, Long userId);
}
