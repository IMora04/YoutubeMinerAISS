package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.channel.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChannelServiceTest {

    @Autowired
    ChannelService service;

    @Test
    void getVideosChannel(){
        for(Channel c:service.getChannels().getItems()) {
            System.out.println(c.getId() + ", " + c.getSnippet());
        }
    }

}
