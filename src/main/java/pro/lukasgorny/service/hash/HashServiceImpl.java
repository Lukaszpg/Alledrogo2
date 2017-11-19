package pro.lukasgorny.service.hash;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HashServiceImpl implements HashService {

    private final Integer HASH_LENGTH = 8;

    @Value("${id.hash.password}")
    private String hashPassword;

    public String encode(Long id) {
        Hashids hashids = new Hashids(hashPassword, HASH_LENGTH);
        return hashids.encode(id);
    }

    public Long decode(String hash) {
        Hashids hashids = new Hashids(hashPassword, HASH_LENGTH);
        return hashids.decode(hash)[0];
    }
}
