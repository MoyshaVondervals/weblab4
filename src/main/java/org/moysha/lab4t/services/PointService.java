package org.moysha.lab4t.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.Point;
import org.moysha.lab4t.repositories.PointRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    @Transactional
    public void addPoint(Point point) {
        pointRepository.save(point);
    }


}
