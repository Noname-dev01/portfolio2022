package portfolio2022.portfolio2022.dailyshop.repository.chargeList;

import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeList;
import portfolio2022.portfolio2022.dailyshop.service.ChargeListSearch;

import java.util.List;

public interface ChargeListRepositoryCustom {

    List<ChargeList> findChargeListBySearch(ChargeListSearch chargeListSearch);
}
