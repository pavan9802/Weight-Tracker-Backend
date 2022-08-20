package com.weighttracker.controller;

import com.weighttracker.model.Weight;

import java.util.Comparator;

public class setCompare implements Comparator<Weight> {
    @Override
    public int compare(Weight a, Weight b) {
       return b.getDate().compareTo(a.getDate());
    }
}
