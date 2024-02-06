package WindGirls.MoneyMinder.dto;

import WindGirls.MoneyMinder.domain.ChatRoom;
import WindGirls.MoneyMinder.domain.Message;
import WindGirls.MoneyMinder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MessageDto {
    @Getter
    public static class Send {
        private String content;
        private Long senderId;
        private Long receiverId;
        private Long chatRoomId;

        public Message toMessage() {
            return Message.builder()
                    .content(content)
                    .sender(User.builder().id(senderId).build())
                    .receiver(User.builder().id(receiverId).build())
                    .chatRoom(ChatRoom.builder().id(chatRoomId).build())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String content;
        private UserDto.Response sender;
        private LocalDateTime sendTime;

        public static Response of(Message message) {
            return Response.builder()
                    .content(message.getContent())
                    .sender(UserDto.Response.of(message.getSender()))
                    .sendTime(message.getSend_time())
                    .build();
        }
    }
}
