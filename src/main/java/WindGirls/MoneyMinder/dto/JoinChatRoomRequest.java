package WindGirls.MoneyMinder.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinChatRoomRequest {
    private Long chatRoomId;
    private Long userId;
}