package portfolio2022.portfolio2022.dailyshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    @NotEmpty
    @Column(unique = true)
    private String userId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @Embedded
    private Address address;
    private String role;
    private LocalDateTime createDate;
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
