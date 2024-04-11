package wendydeluca.U5D8.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wendydeluca.U5D8.entities.BlogPost;
import wendydeluca.U5D8.payloads.BlogPostPayload;
import wendydeluca.U5D8.services.BlogPostsService;

import java.util.List;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    @Autowired
    public BlogPostsService blogPostsService;

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "name")String sortBy){
        return blogPostsService.findAll(page,size,sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //STATUS 201 OK
    public BlogPost saveBlogPost(@RequestBody BlogPostPayload body){
        return blogPostsService.save(body);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost findBlogPostById(@PathVariable long blogPostId){
        return blogPostsService.findById(blogPostId);

    }

    @PutMapping("/{blogPostId}")
    public BlogPost findBlogPostByIdAndUpdate(@PathVariable long blogPostId, @RequestBody BlogPostPayload body){
        return blogPostsService.findByIdAndUpdate(blogPostId,body);
    }

    @DeleteMapping("/{blogPostId}")
    public void deleteBlogPost(@PathVariable long blogPostId){
        this.blogPostsService.findByIdAndDelete(blogPostId);
    }


}

