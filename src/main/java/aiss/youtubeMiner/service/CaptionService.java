package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.caption.Caption;
import aiss.youtubeMiner.model.caption.CaptionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${youtubeminer.token}")
    private String token;

    public List<Caption> findAll(String videoId) {
        String uri = "https://www.googleapis.com/youtube/v3/captions?" +
                "key=" + token +
                "&videoId=" + videoId +
                "&part=snippet,id";
        ResponseEntity<CaptionSearch> response = restTemplate.exchange(
                uri, HttpMethod.GET,null, CaptionSearch.class);
        return response.getBody().getItems();
    }

}
