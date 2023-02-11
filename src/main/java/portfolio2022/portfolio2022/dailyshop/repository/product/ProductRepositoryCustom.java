package portfolio2022.portfolio2022.dailyshop.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;

public interface ProductRepositoryCustom {
    Page<Product> findByCategory(String category,Pageable pageable,ProductListCond productListCond);
}
