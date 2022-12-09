package portfolio2022.portfolio2022.dailyshop.service;

import lombok.Data;
import portfolio2022.portfolio2022.dailyshop.domain.OrderStatus;

@Data
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}
