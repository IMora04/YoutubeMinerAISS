package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.caption.Caption;
import aiss.youtubeMiner.model.caption.CaptionSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    public List<Caption> getCaptions(String videoId) {
        String uri = "https://www.googleapis.com/youtube/v3/captions";
        HttpHeaders header = new HttpHeaders();
        String token = "AIzaSyBJcbUFSXcmamEuytMYVsiVPvdbdQkG6i4";
        uri += "?key=" + token + "&videoId=" + videoId + "&part=snippet, id";
        HttpEntity<CaptionSearch> request = new HttpEntity<>(null, header);
        ResponseEntity<CaptionSearch> response = restTemplate.exchange(uri, HttpMethod.GET,
                request, CaptionSearch.class);
        return response.getBody().getItems();
    }

}
