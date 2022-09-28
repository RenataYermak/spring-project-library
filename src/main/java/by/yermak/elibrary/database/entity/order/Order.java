package by.yermak.elibrary.database.entity.order;

import by.yermak.elibrary.database.entity.BaseEntity;
import by.yermak.elibrary.database.entity.book.Book;
import by.yermak.elibrary.database.entity.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "book_id", unique = true, updatable = false)
    private Book book;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", unique = true, updatable = false)
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "status_id", unique = true, updatable = false)
    private OrderStatus status;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "type_id", unique = true, updatable = false)
    private Type type;

    @Column(nullable = false, updatable = false, name = "ordered_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime orderedDate;

    @Column(nullable = false, updatable = false, name = "reserved_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime reservedDate;

    @Column(nullable = false, updatable = false, name = "retrned_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime returnedDate;

    @Column(nullable = false, updatable = false, name = "rejected_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime rejectedDate;
}
