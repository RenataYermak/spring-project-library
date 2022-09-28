package by.yermak.elibrary.database.entity.book;

import by.yermak.elibrary.database.entity.BaseEntity;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "books")
public class Book implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(length = 50)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "publish_year")
    private int publishYear;

    @Column(length = 3000)
    private String description;

    @Column
    private int number;

    @Column
    private String image;

}
