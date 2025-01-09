package org.moysha.lab4t.service;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.AppUser;
import org.moysha.lab4t.models.PointEntity;
import org.moysha.lab4t.repository.PointRepository;
import org.moysha.lab4t.repository.UserRepository;
import org.moysha.lab4t.validators.CheckArea;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PointService {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    private final CheckArea checkArea;



    public List<PointEntity> findAllByUsername(String username) {
        System.out.printf("Get points by username: %s\n", username);
        Integer userId = userRepository.findIdByUsername(username);
        List<PointEntity> pointEntities = pointRepository.findAllByAppUser_Id(userId);
        if (pointEntities == null) {
            return new ArrayList<>();
        }
        return pointEntities;
    }

    public PointEntity saveForUser(PointEntity newPointEntity, String username) {

        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));


        boolean isHit = checkArea.check(newPointEntity.getX(), newPointEntity.getY(), newPointEntity.getR());
        newPointEntity.setStatus(isHit);
        newPointEntity.setAppUser(user);
        pointRepository.save(newPointEntity);



        return newPointEntity;
    }
}