package by.yermak.elibrary.controller;

import by.yermak.elibrary.service.AuthorService;
import by.yermak.elibrary.service.BookService;
import by.yermak.elibrary.database.entity.book.Book;
import by.yermak.elibrary.dto.bookDto.BookCreateDto;
import by.yermak.elibrary.database.repository.AuthorRepository;
import by.yermak.elibrary.database.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

@Slf4j
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    private final BookRepository bookRepository;

    @PostMapping("/add")
    public String bookCreate(@ModelAttribute BookCreateDto book,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("book", book);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/book/add";
        }
        bookService.create(book);
        return "addBook";
    }

    @GetMapping("/{id}")
    public String bookFindById(@PathVariable("id") Long bookId, Model model) {
        return bookService.findById(bookId)
                .map(book -> {
                    model.addAttribute("book", book);
                   // model.addAttribute("authors", authorService.findAll());
                    return "book";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/{id}/edit")
    public String bookEdit(@PathVariable(value = "id") long bookId, Model model) {
        if (!bookRepository.existsById(bookId)) {
            return "redirect:/";
        }
        Optional<Book> book = bookRepository.findById(bookId);
        ArrayList<Book> result = new ArrayList<>();
        book.ifPresent(result::add);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("book", result);
        return "editBook";
    }


    @PostMapping("/{id}/edit")
    public String bookEdit(@PathVariable(value = "id") long bookId, @ModelAttribute BookCreateDto book) {
        return bookService.update(bookId, book)
                .map(it -> "redirect:/book/{id}/edit")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long bookId) {
        if (!bookService.delete(bookId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/";
    }
}
