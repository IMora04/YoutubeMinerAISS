package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.comment.Comment;
import aiss.youtubeMiner.model.comment.CommentSnippet__1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServiceTests {
    @Autowired
    CommentService commentService;
    @Test
    @DisplayName("Test get comments of a given video")
    void getComments(){
        for(Comment c: commentService.getComments("Qyiautg41h8")){
            CommentSnippet__1 comm = c.getCommentSnippet().getTopLevelComment().getSnippet();
            System.out.println("Author: " + comm.getAuthorDisplayName()
                    + "\nPublished: " + comm.getPublishedAt()
                    + "\nChannel: " + comm.getAuthorChannelUrl());
        }
    }
    @Test
    @DisplayName("Test get comments of a video, limiting the amount you want to receive")
    void getCommentsFiltered(){
        for(Comment c:commentService.getComments("Qyiautg41h8",1)){
            CommentSnippet__1 comm = c.getCommentSnippet().getTopLevelComment().getSnippet();
            System.out.println("Author: " + comm.getAuthorDisplayName()
                    + "\nPublished: " + comm.getPublishedAt()
                    + "\nChannel: " + comm.getAuthorChannelUrl());
        }
    }

}
