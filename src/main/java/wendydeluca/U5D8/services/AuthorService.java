package wendydeluca.U5D8.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wendydeluca.U5D8.entities.Author;
import wendydeluca.U5D8.exceptions.BadRequestException;
import wendydeluca.U5D8.exceptions.NotFoundException;
import wendydeluca.U5D8.payloads.AuthorDTO;
import wendydeluca.U5D8.repositories.AuthorDAO;

import java.io.IOException;

@Service
public class AuthorService {

    @Autowired
    public AuthorDAO authorDAO;

    @Autowired
    public Cloudinary cloudinaryUploader;

    public Page<Author> findAll(int page, int size, String sortBy) { //GET ALL CON PAGINAZIONE
        if (size < 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return authorDAO.findAll(pageable);
    }

    public Author save(AuthorDTO body) {
        // 1. Se l'email dell'autore non é presente,
        if (!authorDAO.existsByEmail(body.email())) {
            // 2. creo un nuovo oggetto Autore con i dati provenienti dal body
            Author newAuthor = new Author(body.name(),body.surname(),body.email(),body.dateOfBirth(),("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname()));
           return authorDAO.save(newAuthor);
           // Se é già presente, lancio eccezione :
        } else throw new BadRequestException("Email '" + body.email() + "'  already exists.");

    }

    public Author findById(long id) {
        return authorDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author update(long id, AuthorDTO updatedBody) {
        Author found = this.findById(id);
        found.setAvatar("https://ui-avatars.com/api/?name=" + updatedBody.name() + "+" + updatedBody.surname());
        found.setName(updatedBody.name());
        found.setSurname(updatedBody.surname());
        found.setEmail(updatedBody.email());
        found.setDateOfBirth(updatedBody.dateOfBirth());
        authorDAO.save(found);
        return found;

    }

    public void delete(long id) {
      Author found = this.findById(id);
      authorDAO.delete(found);
        }

        public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;


        }





}
