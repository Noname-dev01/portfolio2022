package portfolio2022.portfolio2022.dailyshop.repository.product;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Objects;

import static portfolio2022.portfolio2022.dailyshop.domain.entity.QProduct.product;

public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory query;

    public ProductRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public Page<Product> findByCategory(String category,Pageable pageable,ProductListCond productListCond) {
        List<Product> result = query
                .select(product)
                .from(product)
                .where(product.category.eq(category))
                .limit(productListCond.getLimit())
                .orderBy(orderByCategory(productListCond))
                .fetch();

        return new PageImpl<>(result);
    }

    private OrderSpecifier<?> orderByCategory(ProductListCond productListCond) {
        if (Objects.equals(productListCond.getOrderBy(), "new")){
            return product.id.desc();
        } else if (Objects.equals(productListCond.getOrderBy(), "lowPrice")) {
            return product.price.asc();
        }else if (Objects.equals(productListCond.getOrderBy(), "highPrice")){
            return product.price.desc();
        }else {
            return product.id.desc();
        }
    }
}
