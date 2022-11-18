package ru.vasadm.goodsList.data.list;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends MongoRepository<ListEntity, String> {}
