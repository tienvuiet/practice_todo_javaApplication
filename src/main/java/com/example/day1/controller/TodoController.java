package com.example.day1.controller;


import com.example.day1.dto.TodoDTO;
import com.example.day1.model.Todo;
import com.example.day1.service.ITodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TodoController {
    private final ITodoService todoService;
    @GetMapping
    public String home(Model model){
        model.addAttribute("todos", todoService.getAllTodo());
      return "home";
    }
    //add
    @GetMapping("/form-create-todo")
    public String formCreateTodo(
            Model model
    ){
        model.addAttribute("todoDTO", new TodoDTO());
        return "form-add-todo";
    }

    @PostMapping("/create-todo")
    public String createTodo(
            @Valid @ModelAttribute(name = "todoDTO") TodoDTO todoDTO,
            BindingResult result,
            Model model
    ){
        if (result.hasErrors()){
            model.addAttribute("todoDTO", todoDTO);
            return "form-add-todo";
        }
        Todo todo = new Todo();
        todo.setContent(todoDTO.getContent());
        todo.setDueDate(todoDTO.getDueDate());
        todo.setStatus(todoDTO.getStatus());
        todo.setPriority(todoDTO.getPriority());
        todoService.save(todo);
        return "redirect:/";
    }


    //edit
    @GetMapping("/form-edit-todo/{id}")
    public String formEditTodo(
            @PathVariable(name = "id") Long editId,
            Model model
    ){
      Todo todoEdit = todoService.getAllTodo().stream().filter( t -> t.getId().equals(editId)).findFirst().orElse(null);
      model.addAttribute("todoDTO", todoEdit);
      model.addAttribute("id",todoEdit.getId());
      return "form-edit-todo";
    }
    @PostMapping("edit-todo/{id}")
    public String editTodo(
            @Valid @ModelAttribute(name = "todoDTO") TodoDTO todoDTO,
            BindingResult result,
            @PathVariable(name = "id") Long editId,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("todoDTO", todoDTO);
            model.addAttribute("id",editId);
            return "form-edit-todo";
        }
        for (Todo t : todoService.getAllTodo()) {
            if (t.getId().equals(editId)) {
                t.setId(editId);
                t.setContent(todoDTO.getContent());
                t.setDueDate(todoDTO.getDueDate());
                t.setStatus(todoDTO.getStatus());
                t.setPriority(todoDTO.getPriority());
                todoService.save(t);
            }
        }
        return "redirect:/";
    }


    // delete
    @GetMapping("/delete/{id}")
    public String deleteTodo(
            @PathVariable(name = "id") Long deleteId
    ){
        Todo todoDelete = todoService.getAllTodo().stream().filter( t -> t.getId().equals(deleteId)).findFirst().orElse(null);
        todoService.delete(todoDelete);
        return "redirect:/";
    }

}
