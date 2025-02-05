package org.moysha.lab4t.controllers;

import lombok.AllArgsConstructor;
import org.moysha.lab4t.models.PointEntity;
import org.moysha.lab4t.models.PointReqForm;
import org.moysha.lab4t.models.PointRespForm;

import org.moysha.lab4t.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dot")
@AllArgsConstructor
public class PointController {

    private final PointService pointService;



    @GetMapping("/get10")
    public ResponseEntity<List<PointRespForm>> loadUserDots(Authentication authentication) {
        String username = authentication.getName();
        List<PointEntity> pointEntities = pointService.findAllByUsername(username);
        List<PointRespForm> pointRespForms = pointEntities.stream().map(PointRespForm::new).toList();
        return ResponseEntity.ok(pointRespForms);
    }

    @PostMapping
    public ResponseEntity<PointRespForm> save(@RequestBody PointReqForm pointReqForm, Authentication authentication) {
        PointEntity newPointEntity = new PointEntity(pointReqForm);
        String username = authentication.getName();
        PointEntity savedPointEntity = pointService.saveForUser(newPointEntity, username);
        PointRespForm responseDto = new PointRespForm(savedPointEntity);
        return ResponseEntity.ok(responseDto);
    }

}