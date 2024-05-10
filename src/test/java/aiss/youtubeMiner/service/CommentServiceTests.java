package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.comment.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServiceTests {
    @Autowired
    CommentService commentService;
    @Test
    void getComments(){
        for(Comment c: commentService.getComments("Qyiautg41h8")){
            System.out.println(c.getCommentSnippet());
        }
    }
    @Test
    void getCommentsFiltered(){
        for(Comment c:commentService.getComments("Qyiautg41h8",1)){
            System.out.println(c.getCommentSnippet());
        }
    }

}
