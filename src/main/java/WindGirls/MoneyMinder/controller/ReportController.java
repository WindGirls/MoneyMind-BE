package WindGirls.MoneyMinder.controller;

import WindGirls.MoneyMinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;
    //이번 달 총 지출 내역 조회
    @GetMapping("/users/{userId}/total-expenses")
    public ResponseEntity<Integer> getTotalExpensesThisMonth(@PathVariable Long userId) {
        int totalExpenses = reportService.getTotalExpensesThisMonth(userId);
        return ResponseEntity.ok(totalExpenses);
    }
    //카테고리별 이번 달 지출 내역 조회
    @GetMapping("/users/{userId}/category-expenses-percent")
    public ResponseEntity<List<Map<String, Object>>> getCategoryExpensesPercentageThisMonth(@PathVariable Long userId) {
        Map<String, Integer> categoryExpenses = reportService.getCategoryExpensesThisMonth(userId);

        // 총 지출 금액 계산
        int totalExpenses = categoryExpenses.values().stream().mapToInt(Integer::intValue).sum();

        // 카테고리별 지출 금액을 퍼센트로 변환하여 리스트에 저장
        List<Map<String, Object>> categoryExpensesWithPercentage = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : categoryExpenses.entrySet()) {
            String category = entry.getKey();
            int amount = entry.getValue();
            double percentage = (double) amount / totalExpenses * 100;
            int percentageWithoutDecimal = (int) Math.floor(percentage);

            // 카테고리별 지출 내역을 맵으로 생성하여 리스트에 추가
            Map<String, Object> categoryExpenseInfo = new HashMap<>();
            categoryExpenseInfo.put("category", category);
            categoryExpenseInfo.put("amount", amount);
            categoryExpenseInfo.put("percentage", percentageWithoutDecimal);
            categoryExpensesWithPercentage.add(categoryExpenseInfo);
        }

        // 지출 내역이 높은 순으로 리스트 정렬
        categoryExpensesWithPercentage.sort((a, b) -> (Integer) b.get("amount") - (Integer) a.get("amount"));

        return ResponseEntity.ok(categoryExpensesWithPercentage);
    }



}
