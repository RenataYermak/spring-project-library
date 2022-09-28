package by.yermak.elibrary.controller;

import by.yermak.elibrary.dto.userDto.UserReadDto;
import by.yermak.elibrary.service.AuthorService;
import by.yermak.elibrary.service.BookService;
import by.yermak.elibrary.dto.bookDto.BookReadDto;
import by.yermak.elibrary.database.repository.AuthorRepository;
import by.yermak.elibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    private final BookService bookService;
    private final UserService userService;

    //    @GetMapping("/")
//    public String singIn(Model model) {
//        model.addAttribute("title", "страница про нас");
//        return "sing_in";
//    }
    @GetMapping("/books")
    public String bookMain(Model model) {
        Iterable<BookReadDto> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "страница про нас");
        return "about";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Iterable<UserReadDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("title", "страница про нас");
        return "orders";
    }

    @GetMapping("/book/add")
    public String addB(Model model) {
        model.addAttribute("title", "страница про нас");
        model.addAttribute("authors", authorService.findAll());
        return "addBook";
    }

    @GetMapping("/book")
    public String book(Model model) {
        model.addAttribute("title", "страница про нас");
        return "book";
    }
}