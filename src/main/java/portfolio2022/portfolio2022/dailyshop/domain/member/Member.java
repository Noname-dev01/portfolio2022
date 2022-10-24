package portfolio2022.portfolio2022.dailyshop.domain.member;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String password;

    @Embedded
    private Address address;



}
