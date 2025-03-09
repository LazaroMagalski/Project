package com.todoList.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoList.Project.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{   
    
}
