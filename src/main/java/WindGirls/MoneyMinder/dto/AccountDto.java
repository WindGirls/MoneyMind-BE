package WindGirls.MoneyMinder.dto;

import WindGirls.MoneyMinder.domain.Category;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AccountDto {


    private long id;
    private int deposit;
    private int withdrawal;
    private Date times;
    private int balance;
    private String category; // 카테고리 이름 추가

    // 생성자, getter 및 setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }

    public String getCategory_id() {
        return category;
    }


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AccountDto() {

    }


    public LocalDate toLocalDate() {
        // articleRegdate 필드의 값을 LocalDate로 변환하는 로직을 구현합니다.
        Instant instant = times.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

}
