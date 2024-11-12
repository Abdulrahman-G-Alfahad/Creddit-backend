package CodedBTA.api_creddit.repository;

import CodedBTA.api_creddit.service.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostList {

    List<Post> posts = new ArrayList<Post>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
