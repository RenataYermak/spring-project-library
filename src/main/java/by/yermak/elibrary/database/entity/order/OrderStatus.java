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
@Table(name = "order_statuses")
public class OrderStatus implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id ")
    private Long id;

    @Column(length = 45)
    private String name;

    public OrderStatus(String name) {
        this.name = name;
    }
}