package ru.geekbrains.crud;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter

    private Long id;

    @Column(name = "title")
    @Getter
    @Setter

    private String title;

    @Column(name = "price")
    @Getter
    @Setter

    private int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, price = %d]", id, title, price);
    }
}
