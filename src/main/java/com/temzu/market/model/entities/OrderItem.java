package com.temzu.market.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_per_item")
    private Integer pricePerItem;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.pricePerItem = product.getPrice();
        this.price = this.pricePerItem;
    }

    public void incrementQuantity() {
        quantity++;
        price = quantity * pricePerItem;
    }

    public void decrementQuantity() {
        quantity--;
        price = quantity * pricePerItem;
    }
}
