package com.example.todo.controller;

import com.example.todo.entity.ToDo;
import com.example.todo.repository.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ToDoController {

    private final ToDoRepository repo;

    public ToDoController(ToDoRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", repo.findAll());
        model.addAttribute("newTodo", new ToDo());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute ToDo todo) {
        repo.save(todo);
        return "redirect:/";
    }

    @GetMapping("/complete/{id}")
    public String complete(@PathVariable Long id) {
        ToDo todo = repo.findById(id).orElseThrow();
        todo.setCompleted(true);
        repo.save(todo);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}
