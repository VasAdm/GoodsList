package ru.vasadm.goodsList.data.list;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListRepository extends MongoRepository<ListEntity, String> {
}
