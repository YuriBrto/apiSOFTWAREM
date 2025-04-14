package com.example.demo.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class professor {
@Getter
@Setter
    @Id
    private Long id;

    @Getter
    @Setter
    private String teacherName;

    @Getter
    @Setter
    private String schoolName;

    @ManyToOne
    @JoinColumn(name ="type.id")
    @Getter
    @Setter
    private Type type;

}
