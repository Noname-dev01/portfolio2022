package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.Builder;
import lombok.Data;
import portfolio2022.portfolio2022.dailyshop.domain.Address;
import portfolio2022.portfolio2022.dailyshop.domain.Member;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String userId;

    private String password;
    private String name;
    private String city;
    private String street;
    private String zipcode;

}
