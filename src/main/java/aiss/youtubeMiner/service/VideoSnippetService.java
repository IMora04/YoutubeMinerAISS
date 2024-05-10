package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.videoSnippet.VideoSnippet;
import aiss.youtubeMiner.model.videoSnippet.VideoSnippetSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VideoSnippetService {

    @Autowired
    RestTemplate restTemplate;

    public List<VideoSnippet> getVideosChannel(String channelId){
        return getVideosChannel(channelId, 10);
    }

    public List<VideoSnippet> getVideosChannel(String chanelId, Integer numVideos){
        String uri = "https://www.googleapis.com/youtube/v3/search?" +
                "key=AIzaSyBJcbUFSXcmamEuytMYVsiVPvdbdQkG6i4" +
                "&part=snippet" +
                "&channelId=" + chanelId +
                "&type=video" +
                "&maxResults=" + numVideos;
        ResponseEntity<VideoSnippetSearch> response = restTemplate.exchange(
                uri, HttpMethod.GET, null, VideoSnippetSearch.class);
        return response.getBody().getItems();
    }

}
