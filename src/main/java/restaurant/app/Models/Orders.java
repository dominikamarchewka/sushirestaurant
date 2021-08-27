package restaurant.app.Models;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private String payment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Customer customer;
}