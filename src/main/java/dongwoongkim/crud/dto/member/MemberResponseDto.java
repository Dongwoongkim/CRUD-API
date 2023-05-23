package dongwoongkim.crud.dto.member;

import dongwoongkim.crud.domain.Member;
import dongwoongkim.crud.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private MemberRole role;

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getUsername(),
                member.getPassword(),
                member.getNickname(),
                member.getEmail(),
                member.getRole());
    }
}
