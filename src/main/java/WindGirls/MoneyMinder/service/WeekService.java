package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.domain.Account;
import WindGirls.MoneyMinder.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeekService {
    private final AccountRepository accountRepository;

    // 해당 주의 요일별 지출 내역 조회
    public Map<DayOfWeek, Integer> getExpensesByDayOfWeek(Long userId) {
        Map<DayOfWeek, Integer> expensesByDayOfWeek = new HashMap<>();

        // 현재 날짜의 시작 요일부터 이번 주의 끝 요일까지 반복
        LocalDate currentDate = LocalDate.now().with(DayOfWeek.MONDAY); // 이번 주의 월요일로 설정
        LocalDate endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY); // 이번 주의 일요일로 설정

        while (!currentDate.isAfter(endOfWeek)) {
            LocalDateTime startOfDay = currentDate.atStartOfDay();
            LocalDateTime endOfDay = currentDate.atTime(23, 59, 59);

            // 해당 날짜의 지출 내역 조회
            List<Account> expenses = accountRepository.findByUserIdAndTimesBetween(
                    userId,
                    Timestamp.valueOf(startOfDay),
                    Timestamp.valueOf(endOfDay)
            );

            // 해당 요일의 총 지출 계산
            int totalExpensesOfDay = expenses.stream()
                    .mapToInt(Account::getWithdrawal)
                    .sum();

            // 해당 요일에 대한 지출 내역을 맵에 추가
            expensesByDayOfWeek.put(currentDate.getDayOfWeek(), totalExpensesOfDay);

            // 다음 날짜로 이동
            currentDate = currentDate.plusDays(1);
        }

        return expensesByDayOfWeek;
    }

    public Map<DayOfWeek, Integer> getWeeklyExpenses(Long userId) {
        return null;
    }
}
