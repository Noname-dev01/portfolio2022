package portfolio2022.portfolio2022.dailyshop.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import portfolio2022.portfolio2022.dailyshop.exception.NotEnoughStockException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String fileName;
    private String filePath;
    private String descriptionSimple;
    @Column(length = 4000)
    private String descriptionDetail;
    private String category;
    private String subCategory;
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems = new ArrayList<>();
    private String badge; //상품 뱃지[SOLD_OUT,SALE,HOT,NORMAL]
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 상품 등록일

    @PrePersist
    public void createDate(){this.createDate = LocalDate.now();}

    //== 비즈니스 로직 ==//

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void minusStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고수량이 부족합니다.");
        }
        this.stockQuantity = restStock;
    }

}