package pro.lukasgorny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.service.hash.HashService;

/**
 * Created by lukaszgo on 2017-11-22.
 */
@Service
public class AbstractGetService {

    protected final HashService hashService;

    @Autowired
    public AbstractGetService(HashService hashService) {
        this.hashService = hashService;
    }
}
