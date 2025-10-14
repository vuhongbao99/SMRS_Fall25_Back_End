package com.example.smrsservice.controller;


import com.example.smrsservice.dto.common.ResponseDto;
import com.example.smrsservice.dto.project.ProjectCreateDto;
import com.example.smrsservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> createProject(@RequestBody ProjectCreateDto dto) {
        try {
            projectService.createProject(dto);
            return ResponseEntity.ok(ResponseDto.success(null, "Project created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.fail(e.getMessage()));
        }
    }
}
