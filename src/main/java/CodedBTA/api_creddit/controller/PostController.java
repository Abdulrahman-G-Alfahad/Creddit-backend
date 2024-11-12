package CodedBTA.api_creddit.controller;

import CodedBTA.api_creddit.repository.PostList;
import CodedBTA.api_creddit.service.Comment;
import CodedBTA.api_creddit.service.Post;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostList repository;

    long id = 0;
    long commentId = 0;

    @GetMapping("/posts")
    ResponseEntity<Object[]> getPosts(){
        //System.out.println("IT IS COMING HERE");
        return ResponseEntity.status(HttpStatus.OK).body(repository.getPosts().toArray());
    }

    @GetMapping("/posts/{id}")
     ResponseEntity<Post> getById(@PathVariable long id){
        for (Post Cpost : repository.getPosts()){
            if (Cpost.getId().equals(String.valueOf(id))) return ResponseEntity.status(HttpStatus.OK).body(Cpost);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //"Post with ID " + id + " not found"
    }

    @PostMapping("/posts")
    ResponseEntity<Post> addPost(@RequestBody Post post){
        if (post.getTitle() == null || post.getDescription() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(post);
        }
        id += 1;
        post.setId(String.valueOf(id));
        post.setComments(new ArrayList<>());
        repository.getPosts().add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @DeleteMapping("/posts/{id}")
    ResponseEntity<String> deletePost(@PathVariable("id") long id){
        for (Post Cpost: repository.getPosts()){
            if (Cpost.getId().equals(String.valueOf(id))){
                repository.getPosts().remove(Cpost);
                return ResponseEntity.status(HttpStatus.OK).body("Post has been deleted successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with ID " + id + " not found");
    }

    @PostMapping("/posts/{id}/comments")
    ResponseEntity<Comment> addComment(@PathVariable("id") long id, @RequestBody Comment comment){
        Post post = null;
        if (comment.getUsername() == null || comment.getComment() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(comment);
        }
        for(Post Cpost: repository.getPosts()){
            if (Cpost.getId().equals(String.valueOf(id))){
                post = Cpost;
            }
        }
        if (post == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(comment);
        }
        commentId +=1;
        comment.setId(String.valueOf(commentId));
        post.getComments().add(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping("/posts/comments/{id}")
    ResponseEntity<String> deleteComment(@PathVariable("id") long id){
        for (Post Cpost : repository.getPosts()){
            for (Comment comment : Cpost.getComments()){
                if (comment.getId().equals(String.valueOf(id))){
                    Cpost.getComments().remove(comment);
                    return ResponseEntity.status(HttpStatus.OK).body("Comment has been deleted successfully");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
    }

}
