package ru.vasadm.goodsList.data.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import ru.vasadm.goodsList.data.product.ProductEntity;

import java.util.List;

@Getter
@Setter
@ApiModel(description = "wrapper for list entity")
public class ListWithKcal {

    @ApiModelProperty(value = "list entity",position = 1)
    private ListEntity list;
    @ApiModelProperty(value = "sum of kilocalories in all products from the list",position = 2)
    private int sumKcal;

    public ListWithKcal() {
    }

    public ListWithKcal(ListEntity list) {
        this.list = list;
        this.sumKcal = countKcal();
    }

    private int countKcal() {
        List<ProductEntity> products = list.getProducts();
        return products.isEmpty() ? 0 : products
                .stream()
                .map(ProductEntity::getKcal)
                .reduce(0, Integer::sum);
    }
}
