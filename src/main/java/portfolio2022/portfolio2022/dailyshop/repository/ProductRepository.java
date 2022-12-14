package portfolio2022.portfolio2022.dailyshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByCategory(String category, Pageable pageable);
}
