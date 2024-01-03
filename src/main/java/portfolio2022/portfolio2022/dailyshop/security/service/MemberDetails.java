package portfolio2022.portfolio2022.dailyshop.security.service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class MemberDetails implements UserDetails, OAuth2User {

    private Member member;
    private Map<String, Object> attributes;

    //일반 로그인 생성자
    public MemberDetails(Member member){
        this.member = member;
    }

    //OAuth 로그인 생성자
    public MemberDetails(Member member, Map<String, Object> attributes){
        this.member = member;
        this.attributes = attributes;
    }

    /**
     * UserDetails 인터페이스 메소드
     *
     * 해당 User의 권한을 리턴하는 곳!(role)
     * SecurityFilterChain에서 권한을 체크할 때 사용됨
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return String.valueOf(member.getRole());
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return null;
    }

    /**
     * OAuth2User 인터페이스 메소드
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}