package restaurant.app.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private String number;
    private String mail;
    private String street;
    private String apartment;
    private String zipCode;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.MERGE,
            orphanRemoval = true)
    private List<Orders> ordersList;
}