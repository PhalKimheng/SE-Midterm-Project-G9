package se.group9.gicCafe.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// import se.group9.gicCafe.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "zone")
    private String zone;

    @Column(name = "total")
    private double total;

    @Column(name = "cash_received")
    private double cash_received;

    @Column(name = "changed")
    private double changed;

    @ManyToOne
    @JoinColumn(name="uid", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="tid", nullable=false)
    private Tables tables;

    @OneToMany(mappedBy="order")
    private List<OrderDetail> orderDetail = new ArrayList<>();
    
}
