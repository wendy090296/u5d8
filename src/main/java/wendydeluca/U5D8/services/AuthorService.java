package wendydeluca.U5D8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wendydeluca.U5D8.entities.Author;
import wendydeluca.U5D8.exceptions.BadRequestException;
import wendydeluca.U5D8.exceptions.NotFoundException;
import wendydeluca.U5D8.payloads.AuthorDTO;
import wendydeluca.U5D8.repositories.AuthorDAO;

@Service
public class AuthorService {

    @Autowired
    public AuthorDAO authorDAO;


    public Page<Author> findAll(int page, int size, String sortBy) { //GET ALL CON PAGINAZIONE
        if (size < 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return authorDAO.findAll(pageable);
    }

    public Author save(Author body) {
        if (!authorDAO.existsByEmail(body.getEmail())) {
            body.setAvatar("https://ui-avatars.com/api/?name=" + body.getName() + "+" + body.getSurname());
            authorDAO.save(body);
            return body;
        } else throw new BadRequestException("Email '" + body.getEmail() + "'  already exists.");

    }

    public Author findById(long id) {
        return authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author update(long id, AuthorDTO updatedBody) {
        Author found = this.findById(id);
        found.setAvatar("https://ui-avatars.com/api/?name=" + updatedBody.getName() + "+" + updatedBody.getSurname());
        found.setName(updatedBody.getName());
        found.setSurname(updatedBody.getSurname());
        found.setEmail(updatedBody.getEmail());
        found.setDateOfBirth(updatedBody.getDateOfBirth());
        authorDAO.save(found);
        return found;

    }

    public void delete(long id) {
      Author found = this.findById(id);
      authorDAO.delete(found);
        }





}
