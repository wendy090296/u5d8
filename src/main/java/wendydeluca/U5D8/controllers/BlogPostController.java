package wendydeluca.U5D8.controllers;

import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wendydeluca.U5D8.entities.BlogPost;
import wendydeluca.U5D8.exceptions.BadRequestException;
import wendydeluca.U5D8.payloads.BlogPostDTO;
import wendydeluca.U5D8.services.BlogPostsService;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {

    @Autowired
    public BlogPostsService blogPostsService;

    @GetMapping
    public Page<BlogPost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id")String sortBy){
        return blogPostsService.findAll(page,size,sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //STATUS 201 OK
    public BlogPost saveBlogPost(@RequestBody  @Validated BlogPostDTO body, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        return blogPostsService.save(body);
    }

    @GetMapping("/{blogPostId}")
    public BlogPost findBlogPostById(@PathVariable long blogPostId){
        return blogPostsService.findById(blogPostId);

    }

    @PutMapping("/{blogPostId}")
    public BlogPost findBlogPostByIdAndUpdate(@PathVariable long blogPostId, @RequestBody BlogPostDTO body){
        return blogPostsService.findByIdAndUpdate(blogPostId,body);
    }

    @DeleteMapping("/{blogPostId}")
    public void deleteBlogPost(@PathVariable long blogPostId){
        this.blogPostsService.findByIdAndDelete(blogPostId);
    }


}

