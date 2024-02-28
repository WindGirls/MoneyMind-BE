package WindGirls.MoneyMinder.controller;

import WindGirls.MoneyMinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/users/{userId}/total-expenses")
    public ResponseEntity<Integer> getTotalExpensesThisMonth(@PathVariable Long userId) {
        int totalExpenses = reportService.getTotalExpensesThisMonth(userId);
        return ResponseEntity.ok(totalExpenses);
    }

}
