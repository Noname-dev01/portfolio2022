package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.Data;

@Data
public class ProductForm {

    private String name;
    private int price;
    private int stockQuantity;
    private String file;
}
