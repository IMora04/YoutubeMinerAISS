package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.channel.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChannelServiceTest {

    @Autowired
    ChannelService service;

    @Test
    @DisplayName("Test get channels")
    void getChannels(){
        for(Channel c:service.getChannels()) {
            System.out.println("Channel Id: " +c.getId() + "\nTitle: " + c.getSnippet().getTitle()
                    + "\nDescription: " + c.getSnippet().getDescription());
        }
    }

    @Test
    @DisplayName("Test get channel given its Id")
    void getChannelById(){
        Channel c = service.getChannel("UCK8sQmJBp8GCxrOtXWBpyEA");
        System.out.println("Title: " + c.getSnippet().getTitle() +
                "\nDescription: " + c.getSnippet().getDescription());
    }

}
