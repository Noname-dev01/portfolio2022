package portfolio2022.portfolio2022.dailyshop.domain.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String name;
    private int price;
    private String category;
    private String subCategory;
    private int stockQuantity;
    private String descriptionSimple;
    private String descriptionDetail;
    private String badge;
}
