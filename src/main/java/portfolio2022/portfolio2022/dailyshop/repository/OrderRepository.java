package portfolio2022.portfolio2022.dailyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>,OrderRepositoryQuery {
}
