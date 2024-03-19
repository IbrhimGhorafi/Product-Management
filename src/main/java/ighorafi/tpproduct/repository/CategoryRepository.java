package ighorafi.tpproduct.repository;

import ighorafi.tpproduct.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCode(Long codeCategory);
}
