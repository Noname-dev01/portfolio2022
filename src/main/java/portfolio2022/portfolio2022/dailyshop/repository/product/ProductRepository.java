package portfolio2022.portfolio2022.dailyshop.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>,ProductRepositoryCustom {

    Product findProductById(Long id);
}
