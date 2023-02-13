package portfolio2022.portfolio2022.dailyshop.repository.product;

import lombok.Data;

@Data
public class ProductListCond {

    private int limit;
    private String category;
    private String subCategory;
    private String orderBy;
    private int minPrice;
    private int maxPrice;
}
