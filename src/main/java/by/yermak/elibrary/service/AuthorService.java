package by.yermak.elibrary.service;

import by.yermak.elibrary.mapper.bookMapper.AuthorReadMapper;
import by.yermak.elibrary.dto.bookDto.AuthorReadDto;
import by.yermak.elibrary.database.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final AuthorReadMapper authorReadMapper;


    public List<AuthorReadDto> findAll() {
        return authorRepository.findAll().stream()
                .map(authorReadMapper::map)
                .toList();
    }
}