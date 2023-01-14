package portfolio2022.portfolio2022.dailyshop.domain.dto;

import lombok.Data;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Address;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;

@Data
public class JoinDto {
    private String username;
    private String password;
    private String name;
    private String city;
    private String street;
    private String zipcode;
    private String role;
    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .address(new Address(city,street,zipcode))
                .role(role)
                .build();
    }
}
