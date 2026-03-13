package dev.codeio.HelloWorld.controller;

import dev.codeio.HelloWorld.service.TodoService;
import dev.codeio.HelloWorld.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping("/get")
    String getTodo() {
        //todoService.printTodos();
        return "Todo";
    }

    //path Variable
    //READ try, catch method in CRUD
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id) {
        try {
            Todo createdTodo = todoService.getTodoById(id);
            return new ResponseEntity<>(createdTodo, HttpStatus.OK);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        }
    }

    //CREATE in CRUD
    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@RequestBody Todo todo) {
            Todo createdTodo = todoService.createTodo(todo);
            return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }
    //UPDATE in CRUD
    @PutMapping("/{id}")
     public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @RequestBody Todo todo) {
        todo.setId(id);
        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
    }
    //DELETE in CRUD
    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id) {
        todoService.deleteTodoById(id);
    }
    //PAGINATION
    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getTodosPaged (@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(todoService.getAllTodoPages(page, size),HttpStatus.OK);
    }
    //Get All Todos
    @GetMapping
    ResponseEntity<List<Todo>> getTodos() {
        return new ResponseEntity<List<Todo>>(todoService.getTodos(), HttpStatus.OK);
    }
    //@GetMapping("/create")
    //String createUser(@RequestParam String userid, @RequestParam String password) {
    //  return "Todo with UserName" + userid + " " + "Password" + password;
    //}
}