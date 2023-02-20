package portfolio2022.portfolio2022.dailyshop.repository.chargeList;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;
import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeList;
import portfolio2022.portfolio2022.dailyshop.domain.entity.ChargeStatus;
import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderStatus;
import portfolio2022.portfolio2022.dailyshop.domain.entity.QChargeList;
import portfolio2022.portfolio2022.dailyshop.service.ChargeListSearch;

import javax.persistence.EntityManager;
import java.util.List;

import static portfolio2022.portfolio2022.dailyshop.domain.entity.QChargeList.chargeList;
import static portfolio2022.portfolio2022.dailyshop.domain.entity.QOrderItem.orderItem;
import static portfolio2022.portfolio2022.dailyshop.domain.entity.QProduct.product;

public class ChargeListRepositoryImpl implements ChargeListRepositoryCustom{

    private final JPAQueryFactory query;

    public ChargeListRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<ChargeList> findChargeListBySearch(ChargeListSearch chargeListSearch) {
        return query
                .select(chargeList)
                .from(chargeList)
                .where(chargeSearch(chargeListSearch),statusEq(chargeListSearch.getChargeStatus()))
                .fetch();
    }

    private BooleanExpression chargeSearch(ChargeListSearch chargeListSearch) {
        if (!StringUtils.hasText(chargeListSearch.getSearchKeyword())){
            return null;
        }
        return chargeList.member.username.contains(chargeListSearch.getSearchKeyword())
                .or(chargeList.member.name.contains(chargeListSearch.getSearchKeyword()));
    }

    private BooleanExpression statusEq(ChargeStatus chargeStatus) {
        if (chargeStatus == null){
            return null;
        }
        return chargeList.chargeStatus.eq(chargeStatus);
    }
}
