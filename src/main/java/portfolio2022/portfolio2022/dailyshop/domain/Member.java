package portfolio2022.portfolio2022.dailyshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio2022.portfolio2022.dailyshop.domain.Address;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String userId, String password, String name, Address address, List<Order> orders) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.orders = orders;
    }
    public Member(String userId, Address address) {
        this.userId = userId;
        this.address = address;
    }
}
