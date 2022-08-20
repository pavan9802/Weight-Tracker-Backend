package com.weighttracker.repository;

import com.weighttracker.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface WeightRepository extends JpaRepository <Weight, Integer> {


    List<Weight> findAllByEmail( String email);

    void deleteWeightByIdAndEmail(int id, String email);

    Weight findWeightByIdAndEmail(int id,  String email);

    List<Weight> findWeightsByWeightAndEmail( Double weight, String email);

    Optional<List<Weight>> findWeightByDate(String date);



}
