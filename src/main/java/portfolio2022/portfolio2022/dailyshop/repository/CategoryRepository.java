package portfolio2022.portfolio2022.dailyshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
