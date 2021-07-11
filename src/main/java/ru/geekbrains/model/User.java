package ru.geekbrains.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @ManyToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )

    @Getter
    @Setter
    private List<Product> productList;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("User [id = %d, name = %s]", id, name);
    }
}
