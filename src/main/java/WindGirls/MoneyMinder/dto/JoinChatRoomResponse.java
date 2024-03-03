package WindGirls.MoneyMinder.dto;

import WindGirls.MoneyMinder.domain.ChatRoom;
import WindGirls.MoneyMinder.domain.ChatRoomParticipants;
import WindGirls.MoneyMinder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinChatRoomResponse {
    private Long id;
    private ChatRoom chatRoomId;
    private User userId;

    public  JoinChatRoomResponse(ChatRoomParticipants chatRoomParticipants){
        this.id=chatRoomParticipants.getId();
        this.chatRoomId=chatRoomParticipants.getChatRoom();
        this.userId=chatRoomParticipants.getUser();

    }
}
