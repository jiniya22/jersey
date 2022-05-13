package xyz.applebox.jersey.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import xyz.applebox.jersey.domain.value.UserValue;
import xyz.applebox.jersey.util.DateTimeUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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

    @Column(nullable = false, length = 150)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean active;

    @Column(updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    private User(String type, String email, String name, String sex, LocalDate birthDate, String phoneNumber, String password) {
        this.type = type;
        this.email = email;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public static User of(UserValue.Request.UserNB u) {
        return builder().type(u.type()).email(u.email()).name(u.name()).sex(u.sex())
                .birthDate(Optional.ofNullable(u.birthDate())
                        .map(birthDate -> LocalDate.parse(birthDate, DateTimeUtils.DATE_FORMATTER))
                        .orElse(null))
                .phoneNumber(u.phoneNumber()).password(u.password()).build();
    }

}
