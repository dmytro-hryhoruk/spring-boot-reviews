package mate.academy.springbootamazon.repository;


import mate.academy.springbootamazon.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmazonEntityRepository extends JpaRepository<ReviewEntity, Long> {
}