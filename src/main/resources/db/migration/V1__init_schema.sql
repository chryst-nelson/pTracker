CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    fullname VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id BIGINT NOT NULL,
    CONSTRAINT fk_project_owner FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    status VARCHAR(50),
    dead_line TIMESTAMP,
    created_at TIMESTAMP,
    project_id BIGINT NOT NULL,
    asignee_id BIGINT NOT NULL,
    CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES projects(id),
    CONSTRAINT fk_task_assignee FOREIGN KEY (asignee_id) REFERENCES users(id)
);