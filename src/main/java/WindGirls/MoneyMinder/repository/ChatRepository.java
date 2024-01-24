package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatRoom, Long> {
}
