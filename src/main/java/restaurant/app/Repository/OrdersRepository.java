package restaurant.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.app.Models.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
