package portfolio2022.portfolio2022.dailyshop.repository.order;

import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderItem;
import portfolio2022.portfolio2022.dailyshop.service.OrderSearch;

import java.util.List;

public interface OrderItemRepositoryCustom {
    List<OrderItem> findOrderItemsByMemberId(OrderSearch orderSearch,Long id);
}
