package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.comment.Comment;
import aiss.youtubeMiner.model.videoSnippet.VideoSnippet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
public class VideoSnippetServiceTests {

    @Autowired
    VideoSnippetService service;

    @Test
    @DisplayName("Test get videos of a given channel")
    void getVideosChannel(){
        for(VideoSnippet v: service.getVideosChannel("UCK8sQmJBp8GCxrOtXWBpyEA")){
            System.out.println("Title: " + v.getSnippet().getTitle()
                    + ". Description: " + v.getSnippet().getDescription());
        }
    }

    @Test
    @DisplayName("Test get videos of a channel, limiting the amount we want to receive")
    void getVideosFiltered(){
        for(VideoSnippet v:service.getVideosChannel("UCK8sQmJBp8GCxrOtXWBpyEA",1)){
            System.out.println("Title: " + v.getSnippet().getTitle()
                    + ". Description: " + v.getSnippet().getDescription());
        }
    }
}
