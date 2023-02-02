package portfolio2022.portfolio2022.dailyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findOrderItemsByMemberId(Long id);
}
