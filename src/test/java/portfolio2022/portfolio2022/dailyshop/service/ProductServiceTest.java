package portfolio2022.portfolio2022.dailyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import portfolio2022.portfolio2022.dailyshop.domain.Product;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void 상품등록() throws Exception {
        //given
        Product product = new Product("chanel",10000,100);
        //when
        Long saveProduct = productService.saveProduct(product);

        //then
        assertThat(product.getId()).isEqualTo(saveProduct);
    }
}