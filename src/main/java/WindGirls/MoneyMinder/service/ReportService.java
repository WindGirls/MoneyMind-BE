package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.domain.Account;
import WindGirls.MoneyMinder.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
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
    //카테고리별 이번 달 지출 내역 조회
    public Map<String, Integer> getCategoryExpensesThisMonth(Long userId) {
        LocalDateTime startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59);

        List<Account> expenses = accountRepository.findByUserIdAndTimesBetween(
                userId,
                Timestamp.valueOf(startOfMonth),
                Timestamp.valueOf(endOfMonth)
        );

        Map<String, Integer> categoryExpenses = new HashMap<>();
        for (Account expense : expenses) {
            String category = expense.getCategory().getName();
            int amount = expense.getWithdrawal();
            categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0) + amount);
        }

        return categoryExpenses;
    }



}
