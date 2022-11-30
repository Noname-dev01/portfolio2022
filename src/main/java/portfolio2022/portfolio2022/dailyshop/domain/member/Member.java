package portfolio2022.portfolio2022.dailyshop.domain.member;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String password;

    protected Member() {
    }

    @Builder
    public Member(String userId, String password){
        this.userId = userId;
        this.password = password;
    }
}
