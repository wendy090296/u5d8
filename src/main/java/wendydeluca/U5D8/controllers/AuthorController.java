package wendydeluca.U5D8.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wendydeluca.U5D8.entities.Author;
import wendydeluca.U5D8.services.AuthorService;


import java.util.List;



    @RestController
    @RequestMapping("/authors")
    public class AuthorController {

        @Autowired
        public AuthorService authorService;

        @GetMapping  //GET ALL
        public List<Author> getAllAuthors(){
            return this.authorService.getAuthorsList();
        }

        @PostMapping //SAVE
        @ResponseStatus(HttpStatus.CREATED) //STATUS 201 OK
        public Author saveAuthor(@RequestBody Author newAuthor){
            return authorService.saveAuthor(newAuthor);
        }

        @GetMapping("/{authorId}") //GET BY ID
        public Author findAuthorById(@PathVariable long authorId){
            return authorService.findById(authorId);

        }

        @PutMapping("/{authorId}") // UPDATING 1
        public Author findAuthorByIdAndUpdate(@PathVariable long authorId, @RequestBody Author body){
            return authorService.findByIdAndUpdate(authorId,body);
        }

        @DeleteMapping("/{authorId}") // DELETE 1
        public void deleteAuthor(@PathVariable long authorId){
            authorService.findByIdAndDelete(authorId);
        }


    }


