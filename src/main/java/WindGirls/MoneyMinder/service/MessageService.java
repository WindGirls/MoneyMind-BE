package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.dto.MessageDto;
import WindGirls.MoneyMinder.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(MessageDto.Send message) {
        messageRepository.save(message.toMessage());
        log.debug("메시지 내용: {}", message);
    }

}
