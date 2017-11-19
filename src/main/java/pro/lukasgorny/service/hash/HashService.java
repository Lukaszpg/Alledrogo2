package pro.lukasgorny.service.hash;

public interface HashService {
    String encode(Long id);
    Long decode(String hash);
}
