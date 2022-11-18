package ru.vasadm.goodsList.data.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity add(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public List<ProductEntity> getAll() {
        return new ArrayList<>(productRepository.findAll());
    }

    public Optional<ProductEntity> get(String id) {
       return productRepository.findById(id);
    }

}
