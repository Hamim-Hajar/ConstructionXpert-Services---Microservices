package com.ConstructionXpert.ServiceProjets.service;


import com.ConstructionXpert.ServiceProjets.model.Project;
import com.ConstructionXpert.ServiceProjets.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> updateProject(Long id, Project projectDetails) {
        return projectRepository.findById(id).map(project -> {
            project.setName(projectDetails.getName());
            project.setDescription(projectDetails.getDescription());
            project.setStartDate(projectDetails.getStartDate());
            project.setEndDate(projectDetails.getEndDate());
            project.setBudget(projectDetails.getBudget());
            return projectRepository.save(project);
        });
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);;
    }

    public Boolean existProject(Long id) {
        return projectRepository.existsById(id);
    }
}
