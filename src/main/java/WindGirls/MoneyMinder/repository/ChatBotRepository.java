package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.Account;
import WindGirls.MoneyMinder.domain.ChatBot;
import WindGirls.MoneyMinder.domain.ChatRoom;
import WindGirls.MoneyMinder.dto.AccountDto;
import WindGirls.MoneyMinder.dto.ChatBotMessageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ChatBotRepository extends JpaRepository<Account, Long> {


    @Query("SELECT SUM(a.withdrawal) FROM Account a WHERE a.user.id = ?1 AND a.times BETWEEN ?2 AND ?3")
    Integer findTotalExpensesByUserIdAndDateRange(long userId, Date startDate, Date endDate);

    @Query("SELECT SUM(a.deposit) FROM Account a WHERE a.user.id = ?1 AND a.times BETWEEN ?2 AND ?3")
    Integer findTotalDepositByUserIdAndDateRange(long userId, Date startDate, Date endDate);

    @Query("SELECT SUM(a.withdrawal) FROM Account a WHERE a.user.id = ?1 AND a.times BETWEEN ?2 AND ?3")
    Integer findLastTotalExpensesByUserIdAndDateRange(long userId, Date startDate, Date endDate);


    Account findFirstByUserIdOrderByTimesDesc(long userId);
}
