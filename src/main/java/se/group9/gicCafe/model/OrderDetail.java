package se.group9.gicCafe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sugar")
    private int sugar;

    @Column(name = "cream")
    private boolean cream;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "size")
    private String size;

    @ManyToOne
    @JoinColumn(name="oid", nullable=false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="did")
    private Drink drink;

    @ManyToOne
    @JoinColumn(name="fid")
    private Food food;

    
}
