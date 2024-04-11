package wendydeluca.U5D8.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wendydeluca.U5D8.entities.Author;
import wendydeluca.U5D8.exceptions.BadRequestException;
import wendydeluca.U5D8.payloads.AuthorDTO;
import wendydeluca.U5D8.services.AuthorService;

import java.io.IOException;


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
        public Author saveAuthor(@RequestBody @Validated AuthorDTO body, BindingResult validation){
            // @Validated, ci controlla il payload, in base ai validatori inseriti nel record (nel DTO)
            // BindingResult validation ci serve per analizzare il risultato finale della validazione
            if(validation.hasErrors()){
                System.out.println(validation.getAllErrors());
                throw new BadRequestException(validation.getAllErrors());
            }
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

        @PostMapping("/upload")
       public String uploadAvatar(@RequestParam("avatar")MultipartFile image) throws IOException {
            // "avatar" deve essere esattamente uguale alla chiave del Multipart dove sarà conte il file

           return  this.authorService.uploadImage(image);


        }


    }


