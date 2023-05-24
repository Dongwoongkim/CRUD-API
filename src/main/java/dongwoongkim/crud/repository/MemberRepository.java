package dongwoongkim.crud.repository;

import dongwoongkim.crud.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByNickname(String nickName);
    Optional<Member> findByEmail(String email);
}
