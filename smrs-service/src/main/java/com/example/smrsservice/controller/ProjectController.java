package com.example.smrsservice.controller;

import com.example.smrsservice.dto.request.ProjectCreateRequest;
import com.example.smrsservice.dto.request.ProjectUpdateRequest;
import com.example.smrsservice.entity.Project;
import com.example.smrsservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

  private final ProjectService projectService;

  @GetMapping
  public ResponseEntity<List<Project>> getAll() {
    return ResponseEntity.ok(projectService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Project> getById(@PathVariable Integer id) {
    return ResponseEntity.ok(projectService.getById(id));
  }

  @PostMapping
  public ResponseEntity<Project> create(@RequestBody ProjectCreateRequest request) {
    Project created = projectService.create(request);
    return ResponseEntity.created(URI.create("/api/projects/" + created.getId())).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Project> update(@PathVariable Integer id, @RequestBody ProjectUpdateRequest request) {
    return ResponseEntity.ok(projectService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    projectService.delete(id);
    return ResponseEntity.status(200).build();
  }
}
