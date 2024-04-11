package wendydeluca.U5D8.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BlogPostPayload {
    private String category;
    private String title;
    private String coverUrl;
    private String content;
    private int timeOfReading;
    private long authorId;

}
