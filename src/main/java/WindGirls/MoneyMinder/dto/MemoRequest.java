package WindGirls.MoneyMinder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequest {
    private Long userId;
    private String content;


}
