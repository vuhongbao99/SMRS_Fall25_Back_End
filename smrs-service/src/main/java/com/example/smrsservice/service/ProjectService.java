package com.example.smrsservice.service;
import com.example.smrsservice.dto.project.ProjectCreateDto;
import com.example.smrsservice.entity.Account;
import com.example.smrsservice.entity.Project;
import com.example.smrsservice.entity.ProjectFile;
import com.example.smrsservice.entity.ProjectImage;
import com.example.smrsservice.repository.AccountRepository;
import com.example.smrsservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AccountRepository accountRepository;

    public void createProject(ProjectCreateDto dto) {
        Account owner = accountRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setType(dto.getType());
        project.setDueDate(dto.getDueDate());
        project.setOwner(owner);

        // Map files
        if (dto.getFiles() != null && !dto.getFiles().isEmpty()) {
            dto.getFiles().forEach(f -> {
                ProjectFile file = new ProjectFile();
                file.setFilePath(f.getFilePath());
                file.setType(f.getType());
                file.setProject(project);
                project.getFiles().add(file);
            });
        }

        // Map images
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            dto.getImages().forEach(i -> {
                ProjectImage image = new ProjectImage();
                image.setUrl(i.getUrl());
                image.setProject(project);
                project.getImages().add(image);
            });
        }

        projectRepository.save(project);
    }
}



