package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.comment.CommentSearch;
import aiss.youtubeMiner.model.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    public List<Comment> getComments(String videoId, Integer maxResults) {
        String uri = "https://www.googleapis.com/youtube/v3/commentThreads?" +
                "videoId=" + videoId +
                "&part=snippet" +
                "&maxResults="+ maxResults +
                "&key=AIzaSyBJcbUFSXcmamEuytMYVsiVPvdbdQkG6i4";
        ResponseEntity<CommentSearch> response = restTemplate.exchange(
                uri, HttpMethod.GET,null, CommentSearch.class);
        return response.getBody().getItems();

    }
    public List<Comment> getComments(String videoId){
        return getComments(videoId, 10);
    }

}

