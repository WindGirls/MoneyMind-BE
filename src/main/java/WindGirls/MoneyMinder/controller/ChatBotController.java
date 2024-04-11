package WindGirls.MoneyMinder.controller;

import WindGirls.MoneyMinder.dto.AccountDto;
import WindGirls.MoneyMinder.dto.ChatBotMessageDto;
import WindGirls.MoneyMinder.repository.ChatBotRepository;
import WindGirls.MoneyMinder.service.AccountService;
import WindGirls.MoneyMinder.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // 프론트엔드 도메인으로 수정
@RestController
@RequestMapping("/api/chatBot")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ChatBotController {
    private ChatBotRepository chatBotRepository;
    private final ChatBotService chatBotService;
    private final AccountService accountService;

    @Autowired
    public ChatBotController(ChatBotRepository chatBotRepository, ChatBotService chatBotService, AccountService accountService) {
        this.chatBotRepository = chatBotRepository;
        this.chatBotService = chatBotService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/test", produces = "text/plain; charset=UTF-8")
    public String time() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "안녕하세요. 현재 서버의 시간은 " + sdf.format(new Date()) + " 입니다!";
    }

//    @PostMapping("/chat")
//    public ResponseEntity<String> receiveChat(@RequestBody ChatBotMessageDto chatMessageDto) {
//        try {
//            // 받아온 채팅 데이터를 저장하거나 필요한 작업을 수행합니다.
//            chatBotService.saveChatMessage(chatMessageDto);
//            return new ResponseEntity<>("Chat message received successfully.", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to receive chat message.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/{userId}/monthly")
    public int getMonthlyExpense(
            @PathVariable long userId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        // 사용자의 ID와 조회할 날짜를 기준으로 한달 간의 지출금액 조회
        LocalDate startDate = date.withDayOfMonth(1);
        LocalDate endDate = date.withDayOfMonth(date.lengthOfMonth());

        // LocalDate를 Date로 변환
        Date startDateAsDate = java.sql.Date.valueOf(startDate);
        Date endDateAsDate = java.sql.Date.valueOf(endDate);

        // 지출 합계 조회
        int totalWithdrawal = chatBotService.getTotalWithdrawalByUserIdAndDateRange(userId, startDateAsDate, endDateAsDate);

        return totalWithdrawal;
    }

    @GetMapping("/{userId}/lastmonthly")
    public int getLastMonthlyExpense(
            @PathVariable long userId
    ) {
        System.out.println("hihi");
        // 현재 날짜 기준으로 지난 달 시작일과 종료일 설정
        LocalDate today = LocalDate.now();
        LocalDate lastMonthStartDate = today.minusMonths(1).withDayOfMonth(1);
        LocalDate lastMonthEndDate = today.minusMonths(1).withDayOfMonth(lastMonthStartDate.lengthOfMonth());

        // LocalDate를 Date로 변환
        Date lastMonthStartDate1 = java.sql.Date.valueOf(lastMonthStartDate);
        Date lastMonthEndDate1 = java.sql.Date.valueOf(lastMonthEndDate);

        // 지난 달의 지출 합계 조회
        return chatBotService.getLastTotalWithdrawalByUserIdAndDateRange(userId, lastMonthStartDate1, lastMonthEndDate1);
    }


    @GetMapping("/{userId}/monthlyDeposit")
    public int getMonthlyDeposit(
            @PathVariable long userId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        // 사용자의 ID와 조회할 날짜를 기준으로 한달 간의 지출금액 조회
        LocalDate startDate = date.withDayOfMonth(1);
        LocalDate endDate = date.withDayOfMonth(date.lengthOfMonth());

        // LocalDate를 Date로 변환
        Date startDateAsDate = java.sql.Date.valueOf(startDate);
        Date endDateAsDate = java.sql.Date.valueOf(endDate);

        // 지출 합계 조회
        int totalDeposit = chatBotService.getTotalDepositByUserIdAndDateRange(userId, startDateAsDate, endDateAsDate);

        return totalDeposit;
    }
    @GetMapping("/{userId}/balance")
    public int getBalanceByUserId(
            @PathVariable long userId
    ){
        return chatBotService.getAccountStatusByUser(userId);
    }

}


