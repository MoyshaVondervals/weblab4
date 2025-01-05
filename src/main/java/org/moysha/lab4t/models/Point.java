package org.moysha.lab4t.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "POINTS")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int author;
    @Column(nullable = false)
    private double x;
    @Column(nullable = false)
    private double y;
    @Column(nullable = false)
    private double r;
    @Column(nullable = false)
    private boolean status;


}
