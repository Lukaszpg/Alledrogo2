package pro.lukasgorny.service.message;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.user.MessageResultDto;
import pro.lukasgorny.model.Message;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.MessageRepository;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.DateFormatter;

/**
 * Created by lukaszgo on 2018-02-15.
 */
@Service
public class GetMessageServiceImpl implements GetMessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final HashService hashService;

    @Autowired
    public GetMessageServiceImpl(MessageRepository messageRepository, UserService userService, HashService hashService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.hashService = hashService;
    }

    @Override
    public Integer getNewCountByReceiverId(Long receiverId) {
        return messageRepository.findNewCountByReceiverId(receiverId);
    }

    @Override
    public List<MessageResultDto> getReceivedMessages(String email) {
        User user = userService.getByEmail(email);
        return createDtoListFromEntityList(messageRepository.findReceivedMessages(user.getId()));
    }

    @Override
    public List<MessageResultDto> getSentMessages(String email) {
        User user = userService.getByEmail(email);
        return createDtoListFromEntityList(messageRepository.findSentMessages(user.getId()));
    }

    @Override
    public void setMessageStatusToNotNew(String id) {
        Message message = messageRepository.findOne(hashService.decode(id));
        message.setIsNew(false);
        messageRepository.save(message);
    }

    private List<MessageResultDto> createDtoListFromEntityList(List<Message> messages) {
        return messages != null ? messages.stream().map(this::createDtoFromEntity).collect(Collectors.toList()) : new ArrayList<>();
    }

    private MessageResultDto createDtoFromEntity(Message message) {
        MessageResultDto messageResultDto = new MessageResultDto();
        messageResultDto.setId(hashService.encode(message.getId()));
        messageResultDto.setContent(message.getContent());
        messageResultDto.setTitle(message.getTitle());
        messageResultDto.setSenderEmail(message.getSender().getEmail());
        messageResultDto.setReceiverEmail(message.getReceiver().getEmail());
        messageResultDto.setIsNew(message.getIsNew());
        messageResultDto.setCreated(DateFormatter.formatDateToHourMinuteFormat(message.getCreateDate()));
        messageResultDto.setSenderId(hashService.encode(message.getSender().getId()));
        return messageResultDto;
    }
}
