package wendydeluca.U5D8.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
    @Table(name = "blog_posts")
    @Getter
    @Setter

    @ToString
    @NoArgsConstructor
    public class BlogPost {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Setter(AccessLevel.NONE)
        private long id;
        private String category;
        private String title;
        private String coverUrl;
        private String content;
        private int timeOfReading;
        @ManyToOne
        @JoinColumn(name = "author_id")
        private Author author;


    public BlogPost(String category, String title, String coverUrl, String content, int timeOfReading, Author author) {
        this.category = category;
        this.title = title;
        this.coverUrl = coverUrl;
        this.content = content;
        this.timeOfReading = timeOfReading;
        this.author = author;
    }
}


