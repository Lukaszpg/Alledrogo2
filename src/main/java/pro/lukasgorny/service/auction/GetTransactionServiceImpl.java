package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.auction.TransactionResultDto;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.repository.TransactionRepository;
import pro.lukasgorny.service.hash.HashService;

/**
 * Created by Łukasz on 15.01.2018.
 */
@Service
public class GetTransactionServiceImpl implements GetTransactionService {

    private final TransactionRepository transactionRepository;
    private final HashService hashService;

    @Autowired
    public GetTransactionServiceImpl(TransactionRepository transactionRepository, HashService hashService) {
        this.transactionRepository = transactionRepository;
        this.hashService = hashService;
    }

    @Override
    public TransactionResultDto getWinningBidForAuction(String id) {
        Transaction bid = transactionRepository.findWinningBidForAuction(hashService.decode(id));

        if (bid != null) {
            return createDtoFromObject(bid);
        }

        return null;
    }

    private TransactionResultDto createDtoFromObject(Transaction transaction) {
        TransactionResultDto transactionResultDto = new TransactionResultDto();
        transactionResultDto.setOfferedPrice(transaction.getOffer());
        transactionResultDto.setBuyerName(transaction.getUser().getEmail());
        transactionResultDto.setTransactionType(transaction.getTransactionType());
        return transactionResultDto;
    }
}
