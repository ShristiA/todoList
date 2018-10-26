package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
   @Autowired
   ToDoRepository todorepository;

    @RequestMapping("/")
    public String listToDo(Model model){
        model.addAttribute("todos",todorepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String addingtodolist(Model model){
        model.addAttribute("todo" ,new ToDo());
        return "todoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid ToDo todo, BindingResult result){
        if(result.hasErrors()){
            return "todoform";
        }
        todorepository.save(todo);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showTodoList(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", todorepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updatetodolist(@PathVariable("id")long id, Model model){
      model.addAttribute("todo",todorepository.findById(id).get());
      return "courseform";
    }
    @RequestMapping("/delete/{id}")
    public String deltodolist(@PathVariable("id") long id){
        todorepository.deleteById(id);
        return "redirect:/";
    }


}

