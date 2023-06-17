package portfolio2022.portfolio2022.dailyshop.domain.dto;

import lombok.Data;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Role;

@Data
public class JoinDto {
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phone;
    private Role role;
    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .address(address)
                .email(email)
                .phone(phone)
                .role(role)
                .build();
    }
}
