package com.example.ticket_service.exception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(long id) {
        super("No tasks found for project with id:" + id);
    }
}
