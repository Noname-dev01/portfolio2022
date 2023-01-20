package portfolio2022.portfolio2022.dailyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.entity.CartItem;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    CartItem findCartItemById(Long id);

    List<CartItem> findCartItemByProductId(Long id);
}
