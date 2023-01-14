package portfolio2022.portfolio2022.dailyshop.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;

import java.util.Collection;

public class AccountContext extends User {

    private final Member member;

    public AccountContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}
