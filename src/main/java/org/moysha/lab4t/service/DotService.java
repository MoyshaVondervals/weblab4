package org.moysha.lab4t.service;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.AppUser;
import org.moysha.lab4t.models.Dot;
import org.moysha.lab4t.repository.DotRepository;
import org.moysha.lab4t.repository.UserRepository;
import org.moysha.lab4t.validators.CheckArea;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DotService {

    private final DotRepository dotRepository;
    private final UserRepository userRepository;
    private final CheckArea checkArea;



    public List<Dot> findAllByUsername(String username) {
        System.out.printf("Get points by username: %s\n", username);
        Integer userId = userRepository.findIdByUsername(username);
        List<Dot> dots = dotRepository.findAllByAppUser_Id(userId);
        if (dots == null) {
            return new ArrayList<>();
        }
        return dots;
    }

    public Dot saveForUser(Dot newDot, String username) {

        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));


        boolean isHit = checkArea.check(newDot.getX(), newDot.getY(), newDot.getR());
        newDot.setStatus(isHit);
        newDot.setAppUser(user);
        dotRepository.save(newDot);



        return newDot;
    }
}