package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.domain.Account;
import WindGirls.MoneyMinder.domain.ChatBot;
import WindGirls.MoneyMinder.dto.AccountDto;
import WindGirls.MoneyMinder.dto.ChatBotMessageDto;
import WindGirls.MoneyMinder.repository.AccountRepository;
import WindGirls.MoneyMinder.repository.ChatBotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatBotService {
    private final ChatBotRepository chatBotRepository;
    private final AccountRepository accountRepository;

    public ChatBotService(WindGirls.MoneyMinder.repository.ChatBotRepository chatBotRepository, AccountRepository accountRepository) {
        this.chatBotRepository = chatBotRepository;
        this.accountRepository = accountRepository;
    }



    public int getTotalWithdrawalByUserIdAndDateRange(long userId, Date startDate, Date endDate) {
        Integer TotalWithdrawal = chatBotRepository.findTotalExpensesByUserIdAndDateRange(userId, startDate, endDate);
        return TotalWithdrawal != null ? TotalWithdrawal : 0;
    }

    public int getLastTotalWithdrawalByUserIdAndDateRange(long userId, Date startDate, Date endDate) {
        Integer lastTotalWithdrawal = chatBotRepository.findLastTotalExpensesByUserIdAndDateRange(userId, startDate, endDate);
        return lastTotalWithdrawal != null ? lastTotalWithdrawal : 0;
    }
    public int getTotalDepositByUserIdAndDateRange(long userId, Date startDate, Date endDate) {

        Integer TotalWithdrawal = chatBotRepository.findTotalDepositByUserIdAndDateRange(userId, startDate, endDate);
        return TotalWithdrawal != null ? TotalWithdrawal : 0;
    }

public int getAccountStatusByUser(Long userId) {
    try {
        Account latestAccount = chatBotRepository.findFirstByUserIdOrderByTimesDesc(userId);
        if (latestAccount != null) {
            return latestAccount.getBalance(); // 최신 거래의 잔액 반환
        } else {
            // 사용자의 계정이 없는 경우 0을 반환하거나 예외 처리를 할 수 있습니다.
            return 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
        throw e;
    }
}
    private AccountDto convertToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setDeposit(account.getDeposit());
        accountDto.setWithdrawal(account.getWithdrawal());
        accountDto.setTimes(account.getTimes());
        accountDto.setBalance(account.getBalance());
        // 카테고리 정보 설정
        if (account.getCategory() != null) {
            accountDto.setCategory(account.getCategory().getName());
        }
        return accountDto;
    }
    }

