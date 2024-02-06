package WindGirls.MoneyMinder.service;
import java.time.LocalDate;

import WindGirls.MoneyMinder.domain.Account;
import WindGirls.MoneyMinder.dto.AccountDto;
import WindGirls.MoneyMinder.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDto> getExpensesByDate(LocalDate date) {
        // LocalDate를 java.util.Date로 변환
        Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Repository에서 java.util.Date를 사용한 메서드 호출
        List<Account> accountsDate = accountRepository.findAllByTimesContaining(utilDate);

        // ExpenseModel로 변환 후 반환
        return accountsDate.stream().map(this::convertToExpenseModel).collect(Collectors.toList());
    }

    private AccountDto convertToExpenseModel(Account account) {
        // Account를 ExpenseModel로 변환하는 로직을 여기에 작성
        // 예시로 이름과 잔액만 복사하는 코드를 작성하였습니다.
        AccountDto expenseModel = new AccountDto();
        expenseModel.setId(account.getId());
        expenseModel.setBalance(account.getBalance());
        return expenseModel;
    }

    public List<Account> getAccountsByUserId(Long user_id) {
        System.out.println("서비스 ---------------");
        return accountRepository.findByUserId(user_id);
    }

    public List<AccountDto> getAccountStatusByDateAndUser(Date date, Long userId) {
        try {
            // Repository에서 날짜와 유저에 해당하는 데이터를 가져옴
            List<Account> accounts = accountRepository.findAllByTimesAndUserId(date, userId);
            System.out.println("서비스"+accounts);

            // 가져온 데이터를 AccountDto로 변환
            return accounts.stream()
                    .map(this::convertToAccountDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw e; // 예외를 다시 던져서 외부로 전파
        }
    }


    private List<AccountDto> convertToAccountDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(this::convertToAccountDto)
                .collect(Collectors.toList());
    }

    private AccountDto convertToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setDeposit(account.getDeposit());
        accountDto.setWithdrawal(account.getWithdrawal());
        accountDto.setPlace(account.getPlace());
        accountDto.setTimes(account.getTimes());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }
    }
