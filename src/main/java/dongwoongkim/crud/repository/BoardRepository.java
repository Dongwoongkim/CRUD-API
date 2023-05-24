package dongwoongkim.crud.repository;

import dongwoongkim.crud.domain.Board;
import dongwoongkim.crud.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long>{
}
