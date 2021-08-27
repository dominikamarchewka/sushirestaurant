package restaurant.app.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;
    private String name;
    private double price;
    private String status;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category")
    private Category category;

}