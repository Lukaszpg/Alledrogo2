package pro.lukasgorny.service.message;

import java.util.List;

import pro.lukasgorny.dto.user.MessageResultDto;

/**
 * Created by lukaszgo on 2018-02-15.
 */
public interface GetMessageService {
    Integer getNewCountByReceiverId(Long receiverId);
    List<MessageResultDto> getReceivedMessages(String email);
    List<MessageResultDto> getSentMessages(String email);
    void setMessageStatusToNotNew(String id);
}
