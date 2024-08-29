package com.resource.service;
import com.resource.model.Resource;
import com.resource.repository.ResourceRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final RestTemplate restTemplate;
    private final ResourceRepository resourceRepository;
    private static final String TASK_SERVICE_URL = "http://task-service/api/tasks";

    public Resource createResource(Resource resource) {
        Boolean existTask = restTemplate.getForObject(TASK_SERVICE_URL + "/" + resource.getTaskId() + "/exist", Boolean.class);
        return Boolean.TRUE.equals(existTask) ? resourceRepository.save(resource) : null;
    }

    public List<Resource> getResourcesByTaskId(Long taskId) {
        Boolean existTask = restTemplate.getForObject(TASK_SERVICE_URL + "/" + taskId + "/exist", Boolean.class);
        return Boolean.TRUE.equals(existTask) ? resourceRepository.findByTaskId(taskId) : null;
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Resource updateResource(Long id, Resource resourceDetails) {
        return resourceRepository.findById(id)
                .map(resource -> {
                    resource.setName(resourceDetails.getName());
                    resource.setQuantity(resourceDetails.getQuantity());
                    resource.setType(resourceDetails.getType());
                    resource.setProvider(resourceDetails.getProvider());
                    return resourceRepository.save(resource);
                })
                .orElse(null);
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
