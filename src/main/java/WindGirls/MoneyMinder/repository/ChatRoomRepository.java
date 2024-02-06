package WindGirls.MoneyMinder.repository;

import WindGirls.MoneyMinder.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query(value = "SELECT * FROM chat_room c WHERE (c.sender_id = :senderId AND c.receiver_id = :receiverId) OR (c.sender_id = :receiverId AND c.receiver_id = :senderId)", nativeQuery = true)
    Optional<ChatRoom> findBySender_IdAndReceiver_Id(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
    @Query(value = "SELECT * FROM chat_room c WHERE c.sender_id = :userId OR c.receiver_id = :userId", nativeQuery = true)
    Collection<ChatRoom> findBySender_IdOrReceiver_Id(@Param("userId") Long userId);

}
