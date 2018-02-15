package pro.lukasgorny.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.user.MessageSaveDto;
import pro.lukasgorny.model.Message;
import pro.lukasgorny.repository.MessageRepository;
import pro.lukasgorny.service.user.UserService;

/**
 * Created by lukaszgo on 2018-02-15.
 */
@Service
public class CreateMessageServiceImpl implements CreateMessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public CreateMessageServiceImpl(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public void createMessage(MessageSaveDto messageSaveDto) {
        Message message = createEntityFromDto(messageSaveDto);
        messageRepository.save(message);
    }

    private Message createEntityFromDto(MessageSaveDto messageSaveDto) {
        Message message = new Message();
        message.setContent(messageSaveDto.getContent());
        message.setTitle(messageSaveDto.getTitle());
        message.setSender(userService.getByEmail(messageSaveDto.getSenderEmail()));
        message.setReceiver(userService.getById(messageSaveDto.getReceiverId()));
        message.setIsNew(true);
        return message;
    }
}
