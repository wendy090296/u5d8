package wendydeluca.U5D8.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wendydeluca.U5D8.entities.Author;
import wendydeluca.U5D8.payloads.AuthorDTO;
import wendydeluca.U5D8.services.AuthorService;


@RestController
    @RequestMapping("/authors")
    public class AuthorController {

        @Autowired
        public AuthorService authorService;

        @GetMapping  //GET ALL
        public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy){
            return authorService.findAll(page,size,sortBy);
        }

        @PostMapping //SAVE
        @ResponseStatus(HttpStatus.CREATED) //STATUS 201 OK
        public Author saveAuthor(@RequestBody Author body){
            return authorService.save(body);
        }

        @GetMapping("/{authorId}") //GET BY ID
        public Author findAuthorById(@PathVariable long authorId){
            return authorService.findById(authorId);

        }

        @PutMapping("/{authorId}") // UPDATING 1
        public Author findAuthorByIdAndUpdate(@PathVariable long authorId, @RequestBody AuthorDTO body){
            return authorService.update(authorId,body);
        }

//        @PatchMapping("/authors")
//        public Author findByIdAndPatch(@PathVariable long authorId, @RequestBody AuthorPayload body){
//            return authorService.update(authorId,body);
//        }

        @DeleteMapping("/{authorId}") // DELETE 1
        public void deleteAuthor(@PathVariable long authorId){
            authorService.delete(authorId);
        }


    }


