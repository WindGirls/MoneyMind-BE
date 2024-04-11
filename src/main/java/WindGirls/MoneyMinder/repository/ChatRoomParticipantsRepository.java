package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.ChatRoomParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomParticipantsRepository  extends JpaRepository<ChatRoomParticipants, Long> {
    boolean existsByChatRoomIdAndUserId(Long chatRoomId, Long userId);
}
