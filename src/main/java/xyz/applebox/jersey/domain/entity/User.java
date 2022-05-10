package xyz.applebox.jersey.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Entity
@Table(indexes = {@Index(name = "UK_USER_EMAIL", columnList = "email", unique = true)})
@Where(clause = "active = true")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(length = 10)
    @ColumnDefault("'BASIC'")
    private String type;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 1)
    @ColumnDefault("'M'")
    private String sex;

    private LocalDate birthDate;

    @Column(length = 20)
    private String phoneNumber;

    @JsonIgnore
    @Column(nullable = false, length = 150)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean active;

    @Column(updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
