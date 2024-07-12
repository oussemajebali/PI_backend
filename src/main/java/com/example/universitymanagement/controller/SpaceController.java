package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Space;
import com.example.universitymanagement.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/space")
@RequiredArgsConstructor
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @GetMapping
    public List<Space> getAllSpaces() {
        return spaceService.getAllSpaces();
    }

    @GetMapping("/{id}")
    public Space getSpaceById(@PathVariable int id) {
        return spaceService.getSpaceById(id);
    }

    @PostMapping
    public Space createSpace(@RequestBody Space space) {
        return spaceService.createSpace(space);
    }

    @PutMapping("/{id}")
    public Space updateSpace(@PathVariable int id, @RequestBody Space space) {
        return spaceService.updateSpace(id, space);
    }

    @DeleteMapping("/{id}")
    public void deleteSpace(@PathVariable int id) {
        spaceService.deleteSpace(id);
    }

    @GetMapping("/available")
    public List<Space> getAvailableSpaces() {
        return spaceService.getAvailableSpaces();
    }
}
