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
        System.out.println("서비스 ---------------"+accountRepository.findByUserId(user_id));
        return accountRepository.findByUserId(user_id);
    }
//    public List<Account> getAccountsByTransactionId(Long transaction_id) {
//        System.out.println("서비스 ---------------"+accountRepository.findById(transaction_id));
//        return accountRepository.findById(transaction_id);
//    }

    public List<AccountDto> getAccountStatusByDateAndUser(Date date, Long userId) {
        try {
            List<Account> accounts = accountRepository.findAllByTimesAndUserId(date, userId);
            return accounts.stream()
                    .map(this::convertToAccountDto)
                    .collect(Collectors.toList());
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
        accountDto.setCategory(account.getCategory().getName());
        accountDto.setTimes(account.getTimes());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }


    public void addExpense() {

    }
}
