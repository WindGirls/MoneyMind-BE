package WindGirls.MoneyMinder.dto;

import WindGirls.MoneyMinder.domain.ChatRoom;
import WindGirls.MoneyMinder.domain.Message;
import WindGirls.MoneyMinder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

public class MessageDto {
    @Getter
    public static class Send {
        private String content;
        private Long userId;
        private Long chatRoomId;

        public Message toMessage() {
            return Message.builder()
                    .content(content)
                    .user(User.builder().id(userId).build())
                    .chatRoom(ChatRoom.builder().id(chatRoomId).build())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String content;
        private UserDto.Response user;
        private Date sendTime;

        public static Response of(Message message) {
            return Response.builder()
                    .content(message.getContent())
                    .user(UserDto.Response.of(message.getUser()))
                    .sendTime(message.getSend_time())
                    .build();
        }
    }
}
