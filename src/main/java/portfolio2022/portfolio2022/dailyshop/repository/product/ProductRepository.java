package portfolio2022.portfolio2022.dailyshop.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>,ProductRepositoryCustom {

    Product findProductById(Long id);
}
