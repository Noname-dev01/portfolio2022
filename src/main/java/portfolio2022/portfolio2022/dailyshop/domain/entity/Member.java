package portfolio2022.portfolio2022.dailyshop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String role;

    private int coin;


    @OneToMany(mappedBy = "member")
    private List<ChargeList> chargeList = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime createDate;

    //회원의 카트
    @OneToOne(mappedBy = "member")
    private Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
