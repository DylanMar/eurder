package com.switchfully.eurder.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "item_groups")
public class ItemGroup {

    @Id
    @Column(name = "item_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemGroupId;

    @ManyToOne
    @JoinColumn(name = "item_id_fk")
    private Item item;

    @Column(name = "amount")
    private int amount;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @Column(name = "total_price")
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "order_id_fk")
    private Order order;

    private static final int SHIPPING_DATE_WAITING_TIME_IN_STOCK = 1;
    private static final int SHIPPING_DATE_WAITING_TIME_NOT_IN_STOCK = 7;

    public ItemGroup() {}

    public ItemGroup(Item item, int amount) {
        this.item = item;
        this.amount = amount;
        this.shippingDate = calculateShippingDate(item.getAmountInStock());
        this.totalPrice = calculateTotalPrice(item.getPrice(), amount);
    }

    public LocalDate calculateShippingDate(int amount) {
        if (item.getAmountInStock() > amount) {
            return LocalDate.now().plusDays(SHIPPING_DATE_WAITING_TIME_IN_STOCK);
        } else {
            return  LocalDate.now().plusDays(SHIPPING_DATE_WAITING_TIME_NOT_IN_STOCK);
        }
    }

    private double calculateTotalPrice(double price, int amount) {
        return price * amount;
    }

    public Long getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
