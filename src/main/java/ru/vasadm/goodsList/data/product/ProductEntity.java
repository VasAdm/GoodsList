package ru.vasadm.goodsList.data.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
@Getter
@Setter
@ApiModel(description = "data model of product entity")
public class ProductEntity {
    @Id
    @ApiModelProperty(value = "product id generated by db",position = 1)
    private String id;

    @ApiModelProperty(value = "product name",position = 2)
    private String name;

    @ApiModelProperty(value = "product description",position = 3)
    private String description;

    @ApiModelProperty(value = "product calorie content",position = 4)
    private int kcal;

    public ProductEntity(String name, String description, int kcal) {
        this.name = name;
        this.description = description;
        this.kcal = kcal;
    }
}
