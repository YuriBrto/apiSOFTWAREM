package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Entity
public class Lab {

    @Getter
    @Setter
    @Id
    private long id;

    @Getter
    @Setter
    private String LabName;

    @Getter
    @Setter
    private boolean LabStatus;


}
