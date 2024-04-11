package WindGirls.MoneyMinder.dto;

import java.sql.Timestamp;

public class ChatBotMessageDto {
    private Long id;
    private String content;
    private long cSenderId;
    private Timestamp cTimes;
    public ChatBotMessageDto(Long id,String content,long cSenderId,Timestamp cTimes){
        this.content=content;
        this.id=id;
        this.cTimes=cTimes;
        this.cSenderId=cSenderId;

    }

    // 생성자
    public ChatBotMessageDto() {}

    // getter 및 setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCSenderId() {
        return cSenderId;
    }

    public void setCSenderId(long cSenderId) {
        this.cSenderId = cSenderId;
    }

    public Timestamp getCTimes() {
        return cTimes;
    }

    public void setCTimes(Timestamp cTimes) {
        this.cTimes = cTimes;
    }
}
