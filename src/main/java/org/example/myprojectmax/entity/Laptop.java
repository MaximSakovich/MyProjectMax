package org.example.myprojectmax.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Manager name must be not blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Manager name can contain only latin letters and digital")
    private String brand;

    private String model;

    @OneToMany(mappedBy = "laptop", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Selection> selections;

}
