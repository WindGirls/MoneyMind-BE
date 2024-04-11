package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.Account;
import WindGirls.MoneyMinder.dto.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // 다음과 같이 쿼리를 작성하여 최근 잔액을 가져오는 메서드를 정의합니다.

    List<Account> findAllByTimesContaining(Date date);


    List<Account> findByUserId(Long user_id);

    List<Account> findAllByTimesAndUserId(Date date, Long userId);

    @Query("SELECT SUM(a.withdrawal) FROM Account a JOIN a.user u WHERE u.id = ?1 AND a.times BETWEEN ?2 AND ?3")
    Integer getTotalWithdrawalByUserIdAndDateRange(long userId, LocalDate startDate, LocalDate endDate);


}
