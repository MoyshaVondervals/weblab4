package org.moysha.lab4t.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="Points")
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private AppUser appUser;

    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    @Column(nullable = false)
    private Double r;

    @Column(nullable = false)
    private boolean status;



    public PointEntity(PointReqForm pointReqForm) {
        this.x = pointReqForm.getX();
        this.y = pointReqForm.getY();
        this.r = pointReqForm.getR();
    }
}