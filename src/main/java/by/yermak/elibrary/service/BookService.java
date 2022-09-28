package by.yermak.elibrary.service;

import by.yermak.elibrary.database.entity.book.Book;
import by.yermak.elibrary.mapper.bookMapper.BookCreateDtoMapper;
import by.yermak.elibrary.mapper.bookMapper.BookReadMapper;
import by.yermak.elibrary.dto.bookDto.BookCreateDto;
import by.yermak.elibrary.dto.bookDto.BookReadDto;
import by.yermak.elibrary.database.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BookReadMapper bookReadMapper;
    private final BookCreateDtoMapper bookCreateDtoMapper;
    private final ImageService imageService;

    public Optional<BookReadDto> findById(Long id) {
        return bookRepository.findById(id)
                .map(bookReadMapper::map);
    }

    public List<BookReadDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookReadMapper::map)
                .toList();
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @Transactional
    public BookReadDto create(BookCreateDto bookDto) {
        return Optional.of(bookDto)
                .map(dto -> {
                    uploadImage(dto.image());
                    return bookCreateDtoMapper.map(dto);
                })
                .map(bookRepository::save)
                .map(bookReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<BookReadDto> update(Long id, BookCreateDto bookDto) {
        return bookRepository.findById(id)
                .map(entity -> {
                    uploadImage(bookDto.image());
                    return bookCreateDtoMapper.map(bookDto, entity);
                })
                .map(bookRepository::saveAndFlush)
                .map(bookReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return bookRepository.findById(id)
                .map(entity -> {
                    bookRepository.delete(entity);
                    bookRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
