package dev.codeio.HelloWorld.service;

import dev.codeio.HelloWorld.models.Todo;
import dev.codeio.HelloWorld.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

//Bean
@Service
public class TodoService {
    //AutoWire
    @Autowired
    private TodoRepository todoRepository;


    //CRUD operation
    //CREATE in CRUD
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    //READ in CRUD
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found"));
    }
    //UPDATE in CRUD
    public Todo updateTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    //DELETE in CRUD
    public void  deleteTodoById(Long id) {
        todoRepository.delete(getTodoById(id));
    }
    //PAGINATION
    public Page<Todo> getAllTodoPages(int page, int size) {
        Pageable pageable = PageRequest.of(page,  size);
        return todoRepository.findAll(pageable);
    }
    //Get All Todos
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }
}
