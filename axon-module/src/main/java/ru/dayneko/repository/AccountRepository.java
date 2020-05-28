package ru.dayneko.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dayneko.model.entity.AccountEntity;

public interface AccountRepository extends MongoRepository<AccountEntity, String> {
}
