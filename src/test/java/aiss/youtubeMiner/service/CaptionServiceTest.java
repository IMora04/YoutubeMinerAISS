package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.caption.Caption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CaptionServiceTest {

    @Autowired
    CaptionService captionService;

    @Test
    void getCaptions() {
        for(Caption c: captionService.findAll("dQnL0re_f9I")) {
            System.out.println(c.getId() + ": " + c.getSnippet().getName() + ", " + c.getSnippet().getLanguage());
        }
    }

}
