package ru.vasadm.goodsList.data.list;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.vasadm.goodsList.data.product.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListService {
    private final ListRepository listRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ListService(ListRepository listRepository, MongoTemplate mongoTemplate) {
        this.listRepository = listRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public ListEntity add(ListEntity listEntity) {
        return listRepository.save(listEntity);
    }

    public List<ListEntity> getAll() {
        return new ArrayList<>(listRepository.findAll());
    }

    public ListWithKcal get(String id) {
        Optional<ListEntity> list = listRepository.findById(id);
        return list.map(ListWithKcal::new).orElseGet(ListWithKcal::new);
    }

    public Long addProductToList(String listId, ProductEntity product) {
        Optional<ListEntity> optionalList = listRepository.findById(listId);


        if (optionalList.isEmpty()) {
            return 0L;
        }

         UpdateResult result = mongoTemplate.update(ListEntity.class)
                .matching(Query.query(Criteria.where("id").is(listId)))
                .apply(new Update().push("products", product))
                .first();

        return result.getModifiedCount();
    }

    public boolean isListExists(String id) {
        return listRepository.existsById(id);
    }
}
