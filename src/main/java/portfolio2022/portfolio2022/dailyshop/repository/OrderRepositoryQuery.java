package portfolio2022.portfolio2022.dailyshop.repository;

import portfolio2022.portfolio2022.dailyshop.domain.Order;
import portfolio2022.portfolio2022.dailyshop.service.OrderSearch;

import java.util.List;

public interface OrderRepositoryQuery {

    List<Order> findAll(OrderSearch orderSearch);
}
