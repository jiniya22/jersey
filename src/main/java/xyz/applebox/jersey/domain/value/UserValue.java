package xyz.applebox.jersey.domain.value;

import xyz.applebox.jersey.domain.entity.User;
import xyz.applebox.jersey.util.DateTimeUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.Optional;

public final class UserValue {

    public static final class Request {
        public record Creation(
                @NotBlank @Pattern(regexp = "^[a-zA-Z가-힣]{2,10}$") String name,
                @NotBlank @Email String email,
                @NotBlank String password,
                @NotBlank @Pattern(regexp = "^(BASIC|OWNER)$") String type,
                @NotBlank @Pattern(regexp = "^[MF]$") String sex,
                @NotBlank @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$") String birthDate,
                @NotBlank @Pattern(regexp = "^01[0179][0-9]{7,8}$") String phoneNumber
        ) {}

        public record Patch(
                @Email String email,
                String password,
                @Pattern(regexp = "^(BASIC|OWNER)$") String type,
                @Pattern(regexp = "^01[0179][0-9]{7,8}$") String phoneNumber
        ) {}
    }

    public static final class Response {
        public record Detail(Long id, String name, String email, String type, String sex, String birthDate, String phoneNumber, String createdAt, String updatedAt) {
            public Detail {
                Objects.requireNonNull(id);
                Objects.requireNonNull(name);
                Objects.requireNonNull(email);
            }

            public static Detail of(User u) {
                return new Detail(u.getId(), u.getName(), u.getEmail(), u.getType(), u.getSex(),
                        Optional.ofNullable(u.getBirthDate()).map(m -> m.format(DateTimeUtils.DATE_FORMATTER)).orElse(null),
                        u.getPhoneNumber(),
                        Optional.ofNullable(u.getCreatedAt()).map(m -> m.format(DateTimeUtils.DATE_TIME_FORMATTER)).orElse(null),
                        Optional.ofNullable(u.getUpdatedAt()).map(m -> m.format(DateTimeUtils.DATE_TIME_FORMATTER)).orElse(null));
            }
        }

        public record Simple(Long id, String name, String email, String type, String sex) {
            public Simple {
                Objects.requireNonNull(id);
                Objects.requireNonNull(name);
                Objects.requireNonNull(email);
            }

            public static Simple of(User u) {
                return new Simple(u.getId(), u.getName(), u.getEmail(), u.getType(), u.getSex());
            }
        }
    }

}
