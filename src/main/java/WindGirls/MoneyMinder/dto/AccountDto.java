package WindGirls.MoneyMinder.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AccountDto {
    private Long id;
    private int balance,deposit,place,withdrawal;
    private Date times;

    public Long getId(Long id) {return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(int withdrawal) {
        this.withdrawal = withdrawal;
    }


    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }


    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    // 생성자
    public AccountDto(Long id, int deposit, int withdrawal, int place, Date times, int balance) {
        this.id = id;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.place = place;
        this.times = times;
        this.balance = balance;
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
