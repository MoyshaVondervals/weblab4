package org.moysha.lab4t.service;

import org.moysha.lab4t.models.Dot;

import java.util.List;


public interface DotService {
    List<Dot> findAllByUsername(String username);
    Dot saveForUser(Dot newDot, String username);
}