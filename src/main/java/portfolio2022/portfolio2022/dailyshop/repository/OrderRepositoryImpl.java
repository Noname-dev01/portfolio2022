package portfolio2022.portfolio2022.dailyshop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Order;
import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderStatus;
import portfolio2022.portfolio2022.dailyshop.domain.entity.QMember;
import portfolio2022.portfolio2022.dailyshop.domain.entity.QOrder;
import portfolio2022.portfolio2022.dailyshop.service.OrderSearch;

import javax.persistence.EntityManager;
import java.util.List;

import static portfolio2022.portfolio2022.dailyshop.domain.entity.QMember.member;
import static portfolio2022.portfolio2022.dailyshop.domain.entity.QOrder.order;

public class OrderRepositoryImpl implements OrderRepositoryQuery{

    private final JPAQueryFactory query;

    public OrderRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public List<Order> findAll(OrderSearch orderSearch) {
        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getOrderStatus()),nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression nameLike(String memberName) {
        if (!StringUtils.hasText(memberName)){
            return null;
        }
        return member.name.like(memberName);
    }

    private BooleanExpression statusEq(OrderStatus orderStatus) {
        if (orderStatus == null){
            return null;
        }
        return order.status.eq(orderStatus);
    }
}
