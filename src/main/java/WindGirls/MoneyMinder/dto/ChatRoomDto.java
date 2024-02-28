package WindGirls.MoneyMinder.dto;

import WindGirls.MoneyMinder.domain.ChatRoom;
import WindGirls.MoneyMinder.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class ChatRoomDto {
    @Getter
    @AllArgsConstructor
    public static class Request {
        private Long senderId;
        private Long receiverId;

        public Request() {
        }

        public ChatRoom toChatRoom() {
            return ChatRoom.builder()
                    .sender(User.builder().id(senderId).build())
                    .receiver(User.builder().id(receiverId).build())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private UserDto.Response sender;
        private UserDto.Response receiver;


        public static Response of(ChatRoom chatRoom) {
            UserDto.Response sender = null, receiver = null;
            if(chatRoom.getSender() != null) sender = UserDto.Response.of(chatRoom.getSender());
            if(chatRoom.getReceiver() != null) receiver = UserDto.Response.of(chatRoom.getReceiver());

            return Response.builder()
                    .id(chatRoom.getId())
                    .sender(sender)
                    .receiver(receiver)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Detail {
        private Long id;
        private UserDto.Response sender;
        private UserDto.Response receiver;
        private List<MessageDto.Response> messages;

        public static Detail of(ChatRoom chatRoom) {
            return Detail.builder()
                    .id(chatRoom.getId())
                    .sender(UserDto.Response.of(chatRoom.getSender()))
                    .receiver(UserDto.Response.of(chatRoom.getReceiver()))
                    .messages(chatRoom.getMessages().stream().map(MessageDto.Response::of).collect(Collectors.toList()))
                    .build();
        }
    }
}
