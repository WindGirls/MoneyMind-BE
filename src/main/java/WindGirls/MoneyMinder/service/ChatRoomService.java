package WindGirls.MoneyMinder.service;


import WindGirls.MoneyMinder.domain.ChatRoom;
import WindGirls.MoneyMinder.domain.ChatRoomParticipants;
import WindGirls.MoneyMinder.domain.User;
import WindGirls.MoneyMinder.dto.JoinChatRoomRequest;
import WindGirls.MoneyMinder.repository.ChatRoomParticipantsRepository;
import WindGirls.MoneyMinder.repository.ChatRoomRepository;
import WindGirls.MoneyMinder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomParticipantsRepository chatRoomParticipantsRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    // 채팅방에 참여
    public boolean joinChatRoom(JoinChatRoomRequest request) throws Exception  {
        try {
            Long chatRoomId = 1L;
            Long userId = request.getUserId();

            log.debug("chatRoomId: {}",chatRoomId);
            log.debug("userId: {}",userId);


            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

            boolean isAlreadyJoined = chatRoomParticipantsRepository.existsByChatRoomIdAndUserId(chatRoomId, userId);

            if (isAlreadyJoined) {
                throw new IllegalStateException("User with ID " + userId + " is already joined in chat room with ID " + chatRoomId);
            }

            ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                    .orElseThrow(() -> new IllegalArgumentException("Chat room not found with ID: " + chatRoomId));


            // 채팅방 참가자 엔티티를 생성하고 저장합니다.
            ChatRoomParticipants participant = new ChatRoomParticipants();
            participant.setChatRoom(chatRoom);
            participant.setUser(user);
            chatRoomParticipantsRepository.save(participant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }
}
