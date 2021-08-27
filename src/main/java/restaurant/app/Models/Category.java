package restaurant.app.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Category {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idCategory;
    private String categoryName;

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.MERGE,
            orphanRemoval = true)
    private List<Product> productList;


    public Category(String categoryName){
        this.categoryName=categoryName;
        this.productList=new ArrayList<>();
    }

    public Category(){};
    public void addProduct(Product product){
        product.setCategory(this);
        productList.add(product);
    }

}