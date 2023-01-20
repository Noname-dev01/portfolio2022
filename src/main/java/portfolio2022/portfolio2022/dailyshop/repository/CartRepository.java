package portfolio2022.portfolio2022.dailyshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cart;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByMemberId(Long id);

    Cart findCartByMemberId(Long id);
}
