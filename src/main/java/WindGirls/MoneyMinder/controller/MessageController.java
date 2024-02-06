package WindGirls.MoneyMinder.controller;

import WindGirls.MoneyMinder.domain.ChatRoom;
import WindGirls.MoneyMinder.dto.ChatRoomDto;
import WindGirls.MoneyMinder.dto.MessageDto;
import WindGirls.MoneyMinder.service.ChatRoomService;
import WindGirls.MoneyMinder.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    @MessageMapping("/chat/send")
    public void chat(MessageDto.Send message) {
        messageService.sendMessage(message);
        messagingTemplate.convertAndSend("/topic/chat/" + message.getReceiverId(), message);
    }

    @PostMapping("/chat/room")
    public ResponseEntity<String> joinChatRoom(@RequestBody ChatRoomDto.Request dto) {
        try {
            Long roomId = chatRoomService.joinChatRoom(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(roomId.toString());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/chat/room")
    public ResponseEntity<List<ChatRoomDto.Response>> getChatRoomList(@RequestParam Long userId) {
        List<ChatRoomDto.Response> roomList = chatRoomService.getRoomList(userId);
        return ResponseEntity.ok(roomList);
    }

    @GetMapping("/chat/room/{roomId}")
    public ResponseEntity<ChatRoomDto.Detail> getChatRoomDetail(@PathVariable Long roomId) {
        ChatRoomDto.Detail roomDetail = chatRoomService.getRoomDetail(roomId);
        return ResponseEntity.ok(roomDetail);
    }
}
