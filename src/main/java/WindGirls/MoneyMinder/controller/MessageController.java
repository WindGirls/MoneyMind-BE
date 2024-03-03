package WindGirls.MoneyMinder.controller;

import WindGirls.MoneyMinder.dto.JoinChatRoomRequest;
import WindGirls.MoneyMinder.dto.MessageDto;
import WindGirls.MoneyMinder.service.ChatRoomService;
import WindGirls.MoneyMinder.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/chat")
public class MessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    //메시지 전송
    @MessageMapping("/send")
    public void chat(MessageDto.Send message) {
        messageService.sendMessage(message);
        log.debug("메시지 내용: {}", message);
        messagingTemplate.convertAndSend("/topic/chat/" + message.getChatRoomId(), message);
    }
    //채팅방 가입
    @PostMapping("/join")
    public ResponseEntity<Boolean> joinChatRoom(@RequestBody JoinChatRoomRequest request) throws Exception {
        return new ResponseEntity<>(chatRoomService.joinChatRoom(request), HttpStatus.OK);
    }

}
