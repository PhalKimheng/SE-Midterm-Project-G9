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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drinks")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "note")
    private String note;
    
    @Column (name="Imagepath")
    private String imagePath;
    
    @ManyToOne
    @JoinColumn(name="dcid", nullable=false)
    private DrinkCategory drinkCategory;

    @OneToMany(mappedBy="drink")
    private List<DrinkSize> drinkSize = new ArrayList<>();

    @OneToMany(mappedBy="drink")
    private List<OrderDetail> orderDetail = new ArrayList<>();
}


