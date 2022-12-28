package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.Data;
import portfolio2022.portfolio2022.dailyshop.domain.Address;
import portfolio2022.portfolio2022.dailyshop.domain.Member;

@Data
public class JoinDto {
    private String userId;
    private String password;
    private String name;
    private String city;
    private String street;
    private String zipcode;
    private String role;
    public Member toEntity() {
        return Member.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .address(new Address(city,street,zipcode))
                .role(role)
                .build();
    }
}
