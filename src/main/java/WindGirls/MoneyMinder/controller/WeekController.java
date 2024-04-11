package WindGirls.MoneyMinder.controller;// WeekController.java

import WindGirls.MoneyMinder.service.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/week")
public class WeekController {
    private final WeekService weekService;

    @GetMapping("/users/{userId}/weekly-expenses")
    public ResponseEntity<Map<DayOfWeek, Integer>> getWeeklyExpenses(@PathVariable Long userId) {
        Map<DayOfWeek, Integer> weeklyExpenses = weekService.getWeeklyExpenses(userId);
        return ResponseEntity.ok(weeklyExpenses);
    }
}
