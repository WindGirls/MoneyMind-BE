package WindGirls.MoneyMinder.service;


import WindGirls.MoneyMinder.domain.ChatRoom;
import WindGirls.MoneyMinder.dto.ChatRoomDto;
import WindGirls.MoneyMinder.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    //채팅방 만들기
    @Transactional(rollbackFor = Exception.class)
    public Long joinChatRoom(ChatRoomDto.Request dto) throws IllegalStateException {
        if(dto.getReceiverId().equals(dto.getSenderId())) {
            throw new IllegalStateException("자신과의 채팅방은 만들 수 없습니다.");
        }
        Optional<ChatRoom> chatRoom = chatRoomRepository.findBySender_IdAndReceiver_Id(dto.getSenderId(), dto.getReceiverId());
        if(chatRoom.isPresent()) {
            return chatRoom.get().getId();
        } else {
            return chatRoomRepository.save(dto.toChatRoom()).getId();
        }
    }
    //내 채팅방 조회
    @Transactional(readOnly = true)
    public List<ChatRoomDto.Response> getRoomList(Long userId) {
        List<ChatRoomDto.Response> responses = chatRoomRepository.findBySender_IdOrReceiver_Id(userId).stream().map(ChatRoomDto.Response::of).collect(Collectors.toList());
        return responses;
    }

    //특정 채팅방 조회
    @Transactional(readOnly = true)
    public ChatRoomDto.Detail getRoomDetail(Long chatRoomId) {
        Optional<ChatRoomDto.Detail> room = chatRoomRepository.findById(chatRoomId).map(ChatRoomDto.Detail::of);
        return room.orElseThrow();
    }

}
