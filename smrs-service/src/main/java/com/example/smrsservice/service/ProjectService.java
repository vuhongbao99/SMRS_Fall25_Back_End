package com.example.smrsservice.service;

import com.example.smrsservice.dto.request.ProjectCreateRequest;
import com.example.smrsservice.dto.request.ProjectUpdateRequest;
import com.example.smrsservice.entity.Project;
import com.example.smrsservice.entity.ProjectMember;
import com.example.smrsservice.repository.AccountRepository;
import com.example.smrsservice.repository.ProjectMemberRepository;
import com.example.smrsservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

  private final ProjectRepository projectRepository;
  private final AccountRepository accountRepository;
  private final ProjectMemberRepository projectMemberRepository;

  public List<Project> getAll() {
    return projectRepository.findAll();
  }

  public Project getById(Integer id) {
    return projectRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
  }

  public Project create(ProjectCreateRequest request) {
    Project project = new Project();
    project.setName(request.getName());
    project.setDescription(request.getDescription());
    if (request.getStatus() != null) {
      project.setStatus(request.getStatus());
    }
    project.setType(request.getType());
    project.setDueDate(request.getDueDate());
    if (request.getOwnerId() != null) {
      var owner = accountRepository.findById(request.getOwnerId())
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner not found"));
      project.setOwner(owner);
    }
    return projectRepository.save(project);
  }

  public Project update(Integer id, ProjectUpdateRequest request) {
    Project project = projectRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    if (request.getName() != null)
      project.setName(request.getName());
    if (request.getDescription() != null)
      project.setDescription(request.getDescription());
    if (request.getStatus() != null)
      project.setStatus(request.getStatus());
    if (request.getType() != null)
      project.setType(request.getType());
    if (request.getDueDate() != null)
      project.setDueDate(request.getDueDate());
    if (request.getOwnerId() != null) {
      var owner = accountRepository.findById(request.getOwnerId())
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner not found"));
      project.setOwner(owner);
    }
    return projectRepository.save(project);
  }

  public void delete(Integer id) {
    Project project = projectRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    projectRepository.delete(project);
  }

  public List<ProjectMember> addMembers(Integer projectId, List<Integer> accountIds) {
    if (accountIds == null || accountIds.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "accountIds must not be empty");
    }
    Project project = projectRepository.findById(projectId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));

    List<ProjectMember> created = new ArrayList<>();
    for (Integer accountId : accountIds) {
      var account = accountRepository.findById(accountId)
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found: " + accountId));

      boolean isExisted = projectMemberRepository.existsByProjectAndAccount(project, account);
      if (isExisted) {
        // skip existing membership
        continue;
      }

      ProjectMember pm = new ProjectMember();
      pm.setProject(project);
      pm.setAccount(account);
      created.add(projectMemberRepository.save(pm));
    }
    return created;
  }
}