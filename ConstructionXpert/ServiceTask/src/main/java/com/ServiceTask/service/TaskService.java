package com.ServiceTask.service;

import com.ServiceTask.model.Task;
import com.ServiceTask.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class TaskService {

    private final RestTemplate restTemplate;
    private final TaskRepository taskRepository;
    private static final String PROJECT_SERVICE_URL = "http://ServiceGestionProjets/api/projects";

    public Task createTask(Task task) {
        Boolean existProject = restTemplate.getForObject(PROJECT_SERVICE_URL + "/" + task.getProjectId() + "/exist", Boolean.class);
        return Boolean.TRUE.equals(existProject) ? taskRepository.save(task) : null;
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        Boolean existProject = restTemplate.getForObject(PROJECT_SERVICE_URL + "/" + projectId + "/exist", Boolean.class);
        return Boolean.TRUE.equals(existProject) ? taskRepository.findByProjectId(projectId) : null;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task resourceDetails) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setDescription(resourceDetails.getDescription());
                    task.setStartDate(resourceDetails.getStartDate());
                    task.setEndDate(resourceDetails.getEndDate());
                    task.setStatus(resourceDetails.getStatus());
                    return taskRepository.save(task);
                })
                .orElse(null);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Boolean existTask(Long id) {
        return taskRepository.findById(id).isPresent();
    }
}
