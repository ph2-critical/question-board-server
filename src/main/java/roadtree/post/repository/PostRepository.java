package roadtree.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roadtree.post.entity.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //Optional<Post> findById(Long id);
    List<Post> findByTitleContaining(String keyword);
}