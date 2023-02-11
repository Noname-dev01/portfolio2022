package portfolio2022.portfolio2022.dailyshop.repository.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;
import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderStatus;
import portfolio2022.portfolio2022.dailyshop.service.OrderSearch;

import javax.persistence.EntityManager;
import java.util.List;

import static portfolio2022.portfolio2022.dailyshop.domain.entity.QMember.member;
import static portfolio2022.portfolio2022.dailyshop.domain.entity.QOrderItem.orderItem;
import static portfolio2022.portfolio2022.dailyshop.domain.entity.QProduct.product;

public class OrderItemRepositoryImpl implements OrderItemRepositoryCustom {

    private final JPAQueryFactory query;

    public OrderItemRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public List<OrderItem> findOrderItemsByMemberId(OrderSearch orderSearch,Long id) {
        return query
                .select(orderItem)
                .from(orderItem)
                .join(orderItem.order.member,member)
                .join(orderItem.product,product)
                .where(member.id.eq(id),statusEq(orderSearch.getOrderStatus()),nameLike(orderSearch.getProductName()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression nameLike(String productName) {
        if (!StringUtils.hasText(productName)){
            return null;
        }
        return product.name.like(productName);
    }

    private BooleanExpression statusEq(OrderStatus orderStatus) {
        if (orderStatus == null){
            return null;
        }
        return orderItem.order.status.eq(orderStatus);
    }
}
