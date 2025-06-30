package com.tcsKart.CartService.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcsKart.ProductService.bean.Product;
import jakarta.persistence.*;

@Entity
public class CartProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartProductId;

    @JsonIgnore
    @ManyToOne //Many Cart Products belongs to one Cart(cart_id) AND make "cart_id" of Foreign Key of "CartProducts" Table
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne //Many Cart Products is of same Product(product_id)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;


    public CartProducts(){

    }

    public CartProducts(Integer cartProductId, Cart cart, Product product, Integer quantity) {
        this.cartProductId = cartProductId;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Integer cartProductId) {
        this.cartProductId = cartProductId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
/*
mysql> desc cart_products;
+-----------------+------+------+-----+---------+----------------+
| Field           | Type | Null | Key | Default | Extra          |
+-----------------+------+------+-----+---------+----------------+
| cart_product_id | int  | NO   | PRI | NULL    | auto_increment |
| quantity        | int  | YES  |     | NULL    |                |
| cart_id         | int  | YES  | MUL | NULL    |                |
| product_id      | int  | YES  | MUL | NULL    |                |
+-----------------+------+------+-----+---------+----------------+

✅ Keys in cart_products table:
Primary Key: cart_product_id
Foreign Keys (should be defined explicitly):
cart_id → references cart(cart_id)
product_id → references product(product_id)

NOTE: MUL in MySQL means that the column is indexed and may be used as a foreign key

*/
