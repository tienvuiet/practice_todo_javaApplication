package com.example.day1.service;

import com.example.day1.model.Todo;

import java.util.List;

public interface ITodoService {
    List<Todo> getAllTodo();
    void save(Todo todo);
    void edit(Todo todo);
    void delete(Todo todo);
}
