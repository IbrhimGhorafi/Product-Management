package ighorafi.tpproduct.repository;

import ighorafi.tpproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByCode(Long codeProduct);

    Optional<Product> findById(Long aLong);

    void deleteByCode(Long idProduct);
}
