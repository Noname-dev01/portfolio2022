package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.Data;
import portfolio2022.portfolio2022.dailyshop.domain.Category;

@Data
public class ProductForm {

    private String name;
    private int price;
    private String category;
    private String subCategory;
    private int stockQuantity;

}
