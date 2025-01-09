package org.moysha.lab4t.models;

import lombok.Data;


@Data
public class PointRespForm {
    private Integer id;
    private Double x;
    private Double y;
    private Double r;
    private Boolean isHit;
    public PointRespForm(PointEntity pointEntity) {
        this.id = pointEntity.getId();
        this.x = pointEntity.getX();
        this.y = pointEntity.getY();
        this.r = pointEntity.getR();
        this.isHit = pointEntity.isStatus();
    }
}