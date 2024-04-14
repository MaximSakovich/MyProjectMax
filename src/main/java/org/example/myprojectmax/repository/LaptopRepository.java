package org.example.myprojectmax.repository;


import org.example.myprojectmax.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    Optional<Laptop> findByBrand (String brandName);
    Optional<Laptop> findByModel(String modelName);
}
