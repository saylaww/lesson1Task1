package uz.pdp.lesson1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    Worker(name, phoneNumber, Address, Department)

    private String name;
    private String phoneNumber;

    @OneToOne
    private Address address;

    @ManyToOne
    private Department department;

}
