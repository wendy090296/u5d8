package wendydeluca.U5D8.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "authors")
    @Getter
    @Setter
    @ToString
@NoArgsConstructor
    public class Author {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Setter(AccessLevel.NONE)
        private long id;
        private String name;
        private String surname;
        private String email;
        private LocalDate dateOfBirth;
        private String avatar;

        @OneToMany
        private List<BlogPost> blogPosts;


    public Author( String name, String surname, String email, LocalDate dateOfBirth, String avatar) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;

    }
}


