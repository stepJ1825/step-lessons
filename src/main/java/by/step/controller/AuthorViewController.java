package by.step.controller;

import by.step.model.Author;
import by.step.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller  // ← Важно: не @RestController!
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorViewController {

    private final AuthorService authorService;

    /**
     * Вариант 1: Возврат имени шаблона (строка)
     */
    @GetMapping("/list")
    public String getAllAuthors(Model model) {
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("pageTitle", "Список авторов");
        Map<String, String> studentMap = List.of("Student1", "Student2", "Student3").stream()
                .collect(Collectors.toMap(s -> s,
                        s -> s.substring(2)));
        model.addAttribute("students", studentMap);
        return "authors/list";  // путь к шаблону: /WEB-INF/views/authors/list.html
    }

    /**
     * Вариант 2: Возврат ModelAndView
     */
    @GetMapping("/list2")
    public ModelAndView getAllAuthorsView() {
        List<Author> authors = authorService.getAuthors();

        ModelAndView modelAndView = new ModelAndView("authors/list");
        modelAndView.addObject("authors", authors);
        modelAndView.addObject("pageTitle", "Список авторов (ModelAndView)");

        return modelAndView;
    }

//    /**
//     * Вариант 3: Детали автора
//     */
//    @GetMapping("/details/{id}")
//    public String getAuthorDetails(@PathVariable("id") Long id, Model model) {
//        // Предположим, что у вас есть метод в сервисе
//        Author author = authorService.getAuthorById(id);
//
//        model.addAttribute("author", author);
//        model.addAttribute("pageTitle", "Автор: " + author.getName());
//
//        return "authors/details";
//    }
}