package org.example.myprojectmax.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Selection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;
    private String element;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private SelectionStatus status;
    private String description;




}
