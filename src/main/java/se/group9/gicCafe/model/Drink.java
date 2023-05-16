package se.group9.gicCafe.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "drinks")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;
    
    @ManyToOne
    @JoinColumn(name="dcid", nullable=false)
    private DrinkCategory drinkCategory;

    @OneToMany(mappedBy="drink")
    private List<DrinkSize> drinkSize = new ArrayList<>();

    @OneToMany(mappedBy="drink")
    private List<OrderDetail> orderDetail = new ArrayList<>();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public DrinkCategory getDrinkCategory() {
        return drinkCategory;
    }

    public void setDrinkCategory(DrinkCategory drinkCategory) {
        this.drinkCategory = drinkCategory;
    }

    public List<DrinkSize> getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(List<DrinkSize> drinkSize) {
        this.drinkSize = drinkSize;
    }

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
