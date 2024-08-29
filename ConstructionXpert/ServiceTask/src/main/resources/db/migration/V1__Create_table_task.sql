CREATE TABLE task (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      description TEXT NOT NULL,
                      start_date DATE NOT NULL,
                      end_date DATE NOT NULL,
                      status ENUM('TODO', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
                      project_id BIGINT NOT NULL
);