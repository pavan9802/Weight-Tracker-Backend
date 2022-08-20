package com.weighttracker.controller;

import com.weighttracker.model.Weight;
import com.weighttracker.service.WeightService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/track")
@CrossOrigin
public class WeightController {

    private WeightService weightService;

    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    @GetMapping("/all-weights")
    public ResponseEntity<List<Weight>> getAll() {
        List<Weight> weights = weightService.all();

        return new ResponseEntity<>(weights, HttpStatus.OK);
    }
    @GetMapping("/all/{email}")
    public ResponseEntity<List<Weight>> getAllWeights (@PathVariable("email") String email) {
        List<Weight> weights = weightService.getAllWeights( email);
        Collections.sort(weights, new setCompare());

        return new ResponseEntity<>(weights, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Weight> addWeight(@RequestBody Weight weight) {
        Weight newWeight = weightService.addWeight(weight);
        System.out.println(weight.getWeight());
        return new ResponseEntity<>(newWeight, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{email}/{id}")
    public ResponseEntity<?> deleteWeight(@PathVariable("id") int id, @PathVariable("email") String email) {
        weightService.deleteWeight(id, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{email}/{id}")
    public ResponseEntity<Weight> updateWeight(@PathVariable("id") int id, @PathVariable("email") String email, @RequestBody Weight updatedWeight) {
        Weight weight = weightService.updateWeight(updatedWeight, id, email);
        return new ResponseEntity<>(weight, HttpStatus.OK);
    }

    @GetMapping("/find/id/{email}/{id}")
    public ResponseEntity<Weight> getWeightById (@PathVariable("id") int id, @PathVariable("email") String email) {
        Weight weight = weightService.findWeightById(id, email);
        return new ResponseEntity<>(weight, HttpStatus.OK);
    }
    @GetMapping("/find/weight/{email}/{weight}")
    public ResponseEntity<List<Weight>> getWeightByWeight ( @PathVariable("email") String email,@PathVariable("weight") double weight) {
        List<Weight> weights = weightService.findWeightsByWeight(email, weight);
        Collections.sort(weights, new setCompare());
        return new ResponseEntity<>(weights, HttpStatus.OK);
    }

    @GetMapping("/find/date/{date}")
    public ResponseEntity<List<Weight>> getWeightsByWeight (@PathVariable("date") String date) {
        List<Weight> weights = weightService.findWeightByDate(date);
        return new ResponseEntity<>(weights, HttpStatus.OK);
    }

}
