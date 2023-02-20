package portfolio2022.portfolio2022.dailyshop.repository.chargeList;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeList;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;

import java.util.List;

public interface ChargeListRepository extends JpaRepository<ChargeList,Long>, ChargeListRepositoryCustom {

    List<ChargeList> findChargeListByMemberId(Long id);

}
