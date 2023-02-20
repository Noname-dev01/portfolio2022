package portfolio2022.portfolio2022.dailyshop.service;

import lombok.Data;
import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeStatus;
import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderStatus;

@Data
public class ChargeListSearch {

    private String searchKeyword;
    private ChargeStatus chargeStatus;
}
