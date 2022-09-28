package by.yermak.elibrary.database.entity.order;

import by.yermak.elibrary.database.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Table(name = "order_types")
public class Type implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_type_id ")
    private Long id;

    @Column(length = 45)
    private String name;

    public Type(String name) {
        this.name = name;
    }
}