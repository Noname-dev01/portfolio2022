package portfolio2022.portfolio2022.dailyshop.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    Page<Product> findByCategory(String category, Pageable pageable, ProductListCond productListCond);

    Page<Product> findByCategoryAndSubCategory(String category, String subCategory, Pageable pageable, ProductListCond productSubListCond);

    Page<Product> productSearch(String searchKeyword,Pageable pageable,ProductSearchCond productSearchCond);

    List<Product> findRelatedProducts(String subCategory,Long productId);
}
