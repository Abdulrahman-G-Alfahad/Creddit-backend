package CodedBTA.api_creddit.service;

import java.util.ArrayList;
import java.util.List;

public class Post {
    String id;
    String title;
    String description;
    List<Comment> comments = new ArrayList<Comment>();

    public Post(String id, String title, String description, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                '}';
    }
}
