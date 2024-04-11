package wendydeluca.U5D8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wendydeluca.U5D8.entities.BlogPost;
import wendydeluca.U5D8.exceptions.NotFoundException;
import wendydeluca.U5D8.payloads.BlogPostPayload;

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

        public BlogPost save(BlogPostPayload body){
            BlogPost newPost = new BlogPost(body.getCategory(), body.getTitle(), body.getContent(), body.getCoverUrl(), body.getTimeOfReading(), authorService.findById(body.getAuthorId()));
            return (BlogPost) blogPostDAO.save(newPost);

        }

        public BlogPost findById(long id){
           return blogPostDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
        }

        public BlogPost findByIdAndUpdate(long id, BlogPostPayload body){
            BlogPost found = this.findById(id);
            found.setCategory(body.getCategory());
            found.setTitle(body.getTitle());
            found.setContent(body.getContent());
            found.setCoverUrl(body.getCoverUrl());
            found.setTimeOfReading(body.getTimeOfReading());
            found.setAuthor(authorService.findById(body.getAuthorId()));
            blogPostDAO.save(found);
            return found;

        }

        public void findByIdAndDelete(long id){
            BlogPost found = this.findById(id);
           blogPostDAO.delete(found);


        }


    }

