package xyz.applebox.jersey.domain.value;

import xyz.applebox.jersey.domain.entity.User;
import xyz.applebox.jersey.util.DateTimeUtils;

import java.util.Objects;
import java.util.Optional;

public class UserValue {

    public record UserData(Long id, String name, String email, String type, String sex, String birthDate, String phoneNumber, String createdAt, String updatedAt) {
        public UserData {
            Objects.requireNonNull(id);
            Objects.requireNonNull(name);
            Objects.requireNonNull(email);
        }

        public static UserData of(User u) {
            return new UserData(u.getId(), u.getName(), u.getEmail(), u.getType(), u.getSex(),
                    Optional.ofNullable(u.getBirthDate()).map(m -> m.format(DateTimeUtils.DATE_FORMATTER)).orElse(null),
                    u.getPhoneNumber(),
                    Optional.ofNullable(u.getCreatedAt()).map(m -> m.format(DateTimeUtils.DATE_TIME_FORMATTER)).orElse(null),
                    Optional.ofNullable(u.getUpdatedAt()).map(m -> m.format(DateTimeUtils.DATE_TIME_FORMATTER)).orElse(null));
        }
    }

    public record UserSimpleData(Long id, String name, String email, String type, String sex) {
        public UserSimpleData {
            Objects.requireNonNull(id);
            Objects.requireNonNull(name);
            Objects.requireNonNull(email);
        }

        public static UserSimpleData of(User u) {
            return new UserSimpleData(u.getId(), u.getName(), u.getEmail(), u.getType(), u.getSex());
        }
    }

}
