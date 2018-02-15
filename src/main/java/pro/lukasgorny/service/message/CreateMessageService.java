package pro.lukasgorny.service.message;

import pro.lukasgorny.dto.user.MessageSaveDto;

/**
 * Created by lukaszgo on 2018-02-15.
 */
public interface CreateMessageService {
    void createMessage(MessageSaveDto messageSaveDto);
}
