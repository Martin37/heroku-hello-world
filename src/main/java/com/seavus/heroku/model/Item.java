package com.seavus.heroku.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@RequiredArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @NonNull
    private String name;
}
