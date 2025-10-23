package com.example.smrsservice.controller;


import com.example.smrsservice.dto.common.ResponseDto;
import com.example.smrsservice.dto.project.ProjectCreateDto;
import com.example.smrsservice.dto.project.ProjectResponse;
import com.example.smrsservice.dto.project.UpdateProjectStatusRequest;
import com.example.smrsservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PatchMapping("/{id}/status")
    public ProjectResponse updateStatus(
            @PathVariable("id") Integer id,
            @RequestBody UpdateProjectStatusRequest request
    ) {
        return projectService.updateProjectStatus(id, request);
    }

    @GetMapping
    public Page<ProjectResponse> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        return projectService.getAll(page, size, sortBy, sortDir);
    }


    @PostMapping
    public ResponseEntity<ResponseDto<Void>> createProject(@RequestBody ProjectCreateDto dto) {
        try {
            projectService.createProject(dto);
            return ResponseEntity.ok(ResponseDto.success(null, "Project created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.fail(e.getMessage()));
        }
    }


    @GetMapping("/search")
    public Page<ProjectResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        return projectService.searchProjects(name, description, page, size, sortBy, sortDir);
    }
}
