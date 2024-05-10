package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.comment.CommentSearch;
import aiss.youtubeMiner.model.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;
    public List<Comment> getComments(String videoId, Integer numComments) {
        String uri = "https://www.googleapis.com/youtube/v3/commentThreads?videoId=" + videoId + "&part=snippet&maxResults="+ numComments+ "&key=AIzaSyBJcbUFSXcmamEuytMYVsiVPvdbdQkG6i4";
        HttpHeaders header = new HttpHeaders();
        HttpEntity<CommentSearch> request = new HttpEntity<>(null, header);
        ResponseEntity<CommentSearch> response = restTemplate.exchange(uri, HttpMethod.GET,
                request, CommentSearch.class);
        return response.getBody().getItems();

    }
    public List<Comment> getComments(String videoId){
        return getComments(videoId, 10);
    }

}

