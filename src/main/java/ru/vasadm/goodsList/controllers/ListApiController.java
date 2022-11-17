package ru.vasadm.goodsList.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vasadm.goodsList.config.SpringfoxConfig;
import ru.vasadm.goodsList.data.list.ListEntity;
import ru.vasadm.goodsList.data.list.ListService;
import ru.vasadm.goodsList.data.list.ListWithKcal;
import ru.vasadm.goodsList.data.product.ProductEntity;
import ru.vasadm.goodsList.data.product.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {SpringfoxConfig.LIST_TAG})
@RequestMapping("/api")
public class ListApiController {
    private final ListService listService;

    private final ProductService productService;

    @Autowired
    public ListApiController(ListService listService, ProductService productService) {
        this.listService = listService;
        this.productService = productService;
    }

    @ApiOperation("operation to add list by passed name")
    @PostMapping("/list/add")
    public ResponseEntity<ListEntity> create(@RequestParam String name) {
        ListEntity list = listService.add(new ListEntity(name));
        return list != null ? ResponseEntity.ok(list) : ResponseEntity.badRequest().build();
    }

    @ApiOperation("operation to return all lists")
    @GetMapping("/lists")
    public ResponseEntity<List<ListEntity>> readAll() {
        List<ListEntity> lists = listService.getAll();
        return lists.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lists);
    }

    @ApiOperation("operation to get list by id")
    @GetMapping("/list/{id}")
    public ResponseEntity<ListWithKcal> read(@PathVariable String id) {
        ListWithKcal list = listService.get(id);
        return list == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }

    @ApiOperation("operation to add product to list by listId and productId")
    @PostMapping("/list/addProduct")
    public ResponseEntity<Long> addProduct(@RequestParam String listId,
                                                 @RequestParam String productId) {
        Optional<ProductEntity> product = productService.get(productId);
        boolean bothExists = listService.isListExists(listId) && product.isPresent();

        if (!bothExists) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listService.addProductToList(listId, product.get()));
    }
}
