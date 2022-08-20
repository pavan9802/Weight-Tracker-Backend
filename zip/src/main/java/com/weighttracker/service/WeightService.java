package com.weighttracker.service;

import com.weighttracker.model.Weight;
import com.weighttracker.repository.WeightRepository;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.List;

@Service
public class WeightService {

    @Autowired
    private WeightRepository weightRepository;


    public WeightService(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    public List<Weight> all (){
        return weightRepository.findAll();
    }

    @Column(scale=2)
    public Weight addWeight(Weight weight) {
        return weightRepository.save(weight);
    }

    public List<Weight> getAllWeights(String email) {
        return weightRepository.findAllByEmail(email);
    }

    public Weight updateWeight(Weight newWeight, int id, String email) {
        Weight weight = weightRepository.findWeightByIdAndEmail(id, email);
        weight.setWeight(newWeight.getWeight());
        weight.setDate(newWeight.getDate());
        return weightRepository.save(weight);
    }


    public void deleteWeight(int id, String email){
        weightRepository.deleteWeightByIdAndEmail(id, email);
    }



    public Weight findWeightById(int id, String email) {
        return weightRepository.findWeightByIdAndEmail(id, email);

    }

    public List<Weight> findWeightsByWeight(String email, double weight){
        return weightRepository.findWeightsByWeightAndEmail(weight, email);

    }

    public List<Weight> findWeightByDate(String date){
        return weightRepository.findWeightByDate((date))
                .orElseThrow(() -> new UserNotFoundException("Date " + date + " was not found"));
    }



}
