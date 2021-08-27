package restaurant.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.app.Models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory_CategoryNameAndStatus(String category, String status);
    List<Product> findAllByStatus(String status);

    Product findByIdProduct(Integer integer);
}

