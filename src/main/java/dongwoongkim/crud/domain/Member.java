package dongwoongkim.crud.domain;

import dongwoongkim.crud.domain.base.BaseEntity;
import dongwoongkim.crud.dto.member.MemberEditRequestDto;
import dongwoongkim.crud.dto.member.MemberRequestDto;
import dongwoongkim.crud.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    public Member(MemberRequestDto memberRequestDto) {
        this.username = memberRequestDto.getUsername();
        this.password = memberRequestDto.getPassword();
        this.nickname = memberRequestDto.getNickname();
        this.email = memberRequestDto.getEmail();
        this.role = memberRequestDto.getRole();
    }

    public Member(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public void updateMember(MemberEditRequestDto memberEditRequestDto) {
        this.password = memberEditRequestDto.getPassword();
        this.email = memberEditRequestDto.getEmail();
        this.nickname = memberEditRequestDto.getNickname();
    }

}
