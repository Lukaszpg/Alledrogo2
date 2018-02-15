package pro.lukasgorny.service.message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.user.MessageResultDto;
import pro.lukasgorny.model.Message;
import pro.lukasgorny.repository.MessageRepository;

/**
 * Created by lukaszgo on 2018-02-15.
 */
@Service
public class GetMessageServiceImpl implements GetMessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public GetMessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Integer getNewCountByReceiverId(Long receiverId) {
        return messageRepository.findNewCountByReceiverId(receiverId);
    }

    private List<MessageResultDto> createDtoListFromEntityList(List<Message> messages) {
        return messages != null ? messages.stream().map(this::createDtoFromEntity).collect(Collectors.toList()) : new ArrayList<>();
    }

    private MessageResultDto createDtoFromEntity(Message message) {
        MessageResultDto messageResultDto = new MessageResultDto();
        messageResultDto.setContent(message.getContent());
        messageResultDto.setTitle(message.getTitle());
        messageResultDto.setSenderEmail(message.getSender().getEmail());
        messageResultDto.setNew(message.getIsNew());
        return messageResultDto;
    }
}
