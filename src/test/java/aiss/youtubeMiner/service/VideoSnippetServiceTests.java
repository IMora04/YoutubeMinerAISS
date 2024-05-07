package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.videoSnippet.VideoSnippet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
public class VideoSnippetServiceTests {

    @Autowired
    @Qualifier("VideoSnippetService")
    VideoSnippetService service;

    @Test
    void getVideosChannel(){
        for(VideoSnippet v: service.getVideosChannel("UCK8sQmJBp8GCxrOtXWBpyEA")){
            System.out.println(v.getSnippet().getTitle());
        }
    }
}
