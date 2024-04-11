package wendydeluca.U5D8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wendydeluca.U5D8.entities.Author;

@Repository
public interface AuthorDAO extends JpaRepository<Author,Long> {
    boolean existsByEmail(String email);
}

