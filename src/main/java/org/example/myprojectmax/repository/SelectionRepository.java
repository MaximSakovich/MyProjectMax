package org.example.myprojectmax.repository;

import org.example.myprojectmax.entity.Selection;
import org.example.myprojectmax.entity.SelectionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface SelectionRepository extends JpaRepository<Selection, Integer> {

    List<Selection> findAll ();

    List<Selection> findAllByStatus(SelectionStatus status);

    List<Selection> findAllByCreateDate(LocalDateTime createDate);

}
