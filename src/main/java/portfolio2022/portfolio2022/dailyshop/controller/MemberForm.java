package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import portfolio2022.portfolio2022.dailyshop.domain.member.Member;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String userId;

    private String password;

    public static Member createMemberForm(MemberForm form){
        Member member = Member.builder()
                .userId(form.getUserId())
                .password(form.getPassword())
                .build();

        return member;
    }
}
