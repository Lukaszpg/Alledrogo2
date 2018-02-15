package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro.lukasgorny.model.Message;
import pro.lukasgorny.util.QueryBody;

/**
 * Created by lukaszgo on 2018-02-15.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(QueryBody.UserQuery.FIND_NEW_COUNT_MESSAGES_BY_RECEIVER_ID)
    Integer findNewCountByReceiverId(@Param("id") Long receiverId);
}
