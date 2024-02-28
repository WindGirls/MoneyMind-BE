package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.domain.Account;
import WindGirls.MoneyMinder.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final AccountRepository accountRepository;

    //이번 달 총지출 내역 조회
    public int getTotalExpensesThisMonth(Long userId) {
        LocalDateTime startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59);
        List<Account> expenses = accountRepository.findByUserIdAndTimesBetween(
                userId,
                Timestamp.valueOf(startOfMonth),
                Timestamp.valueOf(endOfMonth)
        );

        // 총 지출 계산
        int totalExpenses = expenses.stream()
                .mapToInt(Account::getWithdrawal)
                .sum();

        return totalExpenses;
    }



}
