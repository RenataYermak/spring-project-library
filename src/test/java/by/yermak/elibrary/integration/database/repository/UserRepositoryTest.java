package by.yermak.elibrary.integration.database.repository;

import by.yermak.elibrary.database.entity.user.User;
import by.yermak.elibrary.database.repository.UserRepository;
import by.yermak.elibrary.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final TransactionTemplate transactionTemplate;

    @Test
    void delete() {
        var user = userRepository.findByUsername("jj");
        assertTrue(user.isPresent());
        entityManager.flush();
    }

    @Test
    void findById() {
        var user = entityManager.find(User.class, 1);
        assertNotNull(user);
    }

    @Test
    void checkFindByQueries(){
        userRepository.findByUsername("jj");
    }
}
