package portfolio2022.portfolio2022.dailyshop.repository.product;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static portfolio2022.portfolio2022.dailyshop.domain.entity.QProduct.product;

public class ProductRepositoryImpl implements ProductRepositoryCustom{

    private final JPAQueryFactory query;

    public ProductRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public Page<Product> findByCategory(String category, Pageable pageable, ProductListCond productListCond) {
        List<Product> result = query
                .select(product)
                .from(product)
                .where(product.category.eq(category),priceRange(productListCond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderByPrice(productListCond))
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(product.count())
                .from(product)
                .where(product.category.eq(category), priceRange(productListCond));

        return PageableExecutionUtils.getPage(result,pageable,countQuery::fetchOne);
    }

    @Override
    public Page<Product> findByCategoryAndSubCategory(String category, String subCategory, Pageable pageable, ProductListCond productListCond) {
        List<Product> result = query
                .select(product)
                .from(product)
                .where(product.category.eq(category),subCategory(subCategory),priceRange(productListCond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orderByPrice(productListCond))
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(product.count())
                .from(product)
                .where(product.category.eq(category), subCategory(subCategory), priceRange(productListCond));
        return PageableExecutionUtils.getPage(result,pageable,countQuery::fetchOne);
    }

    @Override
    public Page<Product> productSearch(String searchKeyword, Pageable pageable, ProductSearchCond productSearchCond) {
        List<Product> result = query
                .select(product)
                .from(product)
                .where(productNameLike(searchKeyword).or(categoryLike(searchKeyword.toUpperCase())),searchPriceRange(productSearchCond))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(productByPrice(productSearchCond))
                .fetch();
        JPAQuery<Long> countQuery = query
                .select(product.count())
                .from(product)
                .where(productNameLike(searchKeyword).or(categoryLike(searchKeyword.toUpperCase())),searchPriceRange(productSearchCond));
        return PageableExecutionUtils.getPage(result,pageable,countQuery::fetchOne);
    }

    @Override
    public List<Product> findRelatedProducts(String subCategory,Long productId) {
        return query
                .select(product)
                .from(product)
                .where(product.subCategory.eq(subCategory),product.id.ne(productId))
                .limit(8)
                .fetch();
    }

    @Override
    public List<Product> findProductByMen8() {
        return query
                .select(product)
                .from(product)
                .where(product.category.eq("MEN"))
                .limit(8)
                .fetch();
    }

    @Override
    public List<Product> findProductByWomen8() {
        return query
                .select(product)
                .from(product)
                .where(product.category.eq("WOMEN"))
                .limit(8)
                .fetch();
    }

    @Override
    public List<Product> findProductBySports8() {
        return query
                .select(product)
                .from(product)
                .where(product.category.eq("SPORTS"))
                .limit(8)
                .fetch();
    }

    @Override
    public List<Product> findProductByDigital8() {
        return query
                .select(product)
                .from(product)
                .where(product.category.eq("DIGITAL"))
                .limit(8)
                .fetch();
    }

    private BooleanExpression productNameLike(String searchKeyword){
        if (!StringUtils.hasText(searchKeyword)){
            return null;
        }
        return product.name.contains(searchKeyword);
    }
    private BooleanExpression categoryLike(String searchKeyword){
        if (!StringUtils.hasText(searchKeyword)){
            return null;
        }
        return product.category.contains(searchKeyword);
    }
    private BooleanExpression subCategory(String subCategory) {
        if (subCategory == null){
            return null;
        }
        return product.subCategory.eq(subCategory);
    }

    private BooleanExpression priceRange(ProductListCond productListCond) {
        if (productListCond == null){
            return null;
        }
        return product.price.between(productListCond.getMinPrice(),productListCond.getMaxPrice());
    }

    private BooleanExpression searchPriceRange(ProductSearchCond productSearchCond) {
        if (productSearchCond == null){
            return null;
        }
        return product.price.between(productSearchCond.getMinPrice(),productSearchCond.getMaxPrice());
    }

    private OrderSpecifier<?> orderByPrice(ProductListCond productListCond) {
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
    private OrderSpecifier<?> productByPrice(ProductSearchCond productSearchCond) {
        if (Objects.equals(productSearchCond.getOrderBy(), "new")){
            return product.id.desc();
        } else if (Objects.equals(productSearchCond.getOrderBy(), "lowPrice")) {
            return product.price.asc();
        }else if (Objects.equals(productSearchCond.getOrderBy(), "highPrice")){
            return product.price.desc();
        }else {
            return product.id.desc();
        }
    }
}
