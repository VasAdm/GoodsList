package ru.vasadm.goodsList.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasadm.goodsList.config.SpringfoxConfig;
import ru.vasadm.goodsList.data.product.ProductEntity;
import ru.vasadm.goodsList.data.product.ProductService;

import java.util.List;

@RestController
@Api(tags = {SpringfoxConfig.PRODUCT_TAG})
@RequestMapping("/api")
public class ProductApiController {
    private final ProductService productService;

    @Autowired
    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("operation to add product by passed name, description, kcal")
    @PostMapping("/product/add")
    public ResponseEntity<ProductEntity> create(@RequestParam String name,
                                                @RequestParam String description,
                                                @RequestParam int kcal) {
        ProductEntity product = productService.add(new ProductEntity(name, description, kcal));
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.badRequest().build();
    }

    @ApiOperation("operation to return all products")
    @GetMapping("/products")
    public ResponseEntity<List<ProductEntity>> readAll() {
        List<ProductEntity> products = productService.getAll();
        return products.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
    }


    @ApiOperation("operation to get product by id")
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductEntity> read(@PathVariable String id) {
        return productService.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
