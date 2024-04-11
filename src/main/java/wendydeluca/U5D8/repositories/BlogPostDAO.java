package wendydeluca.U5D8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wendydeluca.U5D8.entities.BlogPost;

@Repository
public interface BlogPostDAO extends JpaRepository<BlogPost,Long> {
}
