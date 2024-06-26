package wendydeluca.U5D8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wendydeluca.U5D8.entities.BlogPost;
import wendydeluca.U5D8.exceptions.NotFoundException;
import wendydeluca.U5D8.payloads.BlogPostDTO;

import wendydeluca.U5D8.repositories.BlogPostDAO;




    @Service
    public class BlogPostsService {

        @Autowired
        public BlogPostDAO blogPostDAO;

        @Autowired
        public AuthorService authorService;





        public Page<BlogPost> findAll(int page, int size, String sortBy){ //FIND ALL CON PAGINAZIONE
            Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
            return blogPostDAO.findAll(pageable);
        }

        public BlogPost save(BlogPostDTO body){
            BlogPost newPost = new BlogPost(body.category(), body.title(), body.content(), body.coverUrl(), body.timeOfReading(), authorService.findById(body.authorId()));
            return blogPostDAO.save(newPost);

        }

        public BlogPost findById(long id){
           return blogPostDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
        }

        public BlogPost findByIdAndUpdate(long id, BlogPostDTO body){
            BlogPost found = this.findById(id);
            found.setCategory(body.category());
            found.setTitle(body.title());
            found.setContent(body.content());
            found.setCoverUrl(body.coverUrl());
            found.setTimeOfReading(body.timeOfReading());
            found.setAuthor(authorService.findById(body.authorId()));
            blogPostDAO.save(found);
            return found;

        }

        public void findByIdAndDelete(long id){
            BlogPost found = this.findById(id);
           blogPostDAO.delete(found);


        }


    }

