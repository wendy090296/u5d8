package wendydeluca.U5D8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wendydeluca.U5D8.entities.Author;
import wendydeluca.U5D8.entities.BlogPost;
import wendydeluca.U5D8.exceptions.BadRequestException;
import wendydeluca.U5D8.exceptions.NotFoundException;
import wendydeluca.U5D8.payloads.AuthorPayload;
import wendydeluca.U5D8.repositories.AuthorDAO;



import java.util.Iterator;

import java.util.Random;

@Service
public class AuthorService {

    @Autowired
    public AuthorDAO authorDAO;


    public Page<Author> getAll(int page, int size, String sortBy){ //GET ALL CON PAGINAZIONE
        if(size<50) size = 50;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
       return authorDAO.findAll(pageable);
    }

//    public Author save(AuthorPayload body){
//        if (!authorDAO.existsByEmail(body.getEmail())) {
//            body.setAvatar("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());
//            authorDAO.save(body);
//            return body;
//        } else throw new BadRequestException("Email '" + body.getEmail() + "'  already exists.");
//
//    }

//    public Author findById(long id){
//        Author found = null;
//        for(Author author: getAll()){
//            if(author.getId() == id) found = author;
////            return author;
//        }
//        if(found == null) throw new NotFoundException(id);
//        else return found;
//    }

//    public Author findByIdAndUpdate(long id, Author updatedAuthor){
//        Author found = null;
//        for(Author author: this.getAll()){
//            if(author.getId() == id) {
//                found = author;
//                found.setName(updatedAuthor.getName());
//                found.setSurname(updatedAuthor.getSurname());
//                found.setEmail(updatedAuthor.getEmail());
//                found.setAvatar(updatedAuthor.getAvatar());
//                found.setDateOfBirth(updatedAuthor.getDateOfBirth());
//
//            }
//        }
//        if(found == null) throw new NotFoundException(id);
//        else return found;
//
//    }

    public void findByIdAndDelete(long id){
        Iterator<Author> iterator =getAll().iterator();
        while(iterator.hasNext()){
            Author current = iterator.next();
            if(current.getId() == id){
                iterator.remove();
            }
        }


    }


}
