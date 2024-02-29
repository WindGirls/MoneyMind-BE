package WindGirls.MoneyMinder.service;

import WindGirls.MoneyMinder.dto.MessageDto;
import WindGirls.MoneyMinder.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(MessageDto.Send message) {
        messageRepository.save(message.toMessage());
    }

}
