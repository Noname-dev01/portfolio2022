package portfolio2022.portfolio2022.dailyshop.repository.product;

import lombok.Data;

@Data
public class ProductSearchCond {

    private int limit;
    private String searchKeyword;
    private String orderBy;
    private int minPrice;
    private int maxPrice;
}
