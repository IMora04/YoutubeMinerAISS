package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.channel.Channel;
import aiss.youtubeMiner.model.channel.ChannelSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChannelService {

    @Value("youtubeminer.token")
    private String token;

    @Autowired
    RestTemplate restTemplate;
    public List<Channel> getChannels(){
        String maxResults = "25";
        String uri = "https://www.googleapis.com/youtube/v3/search?" +
                "key=" + token +
                "&part=snippet" +
                "&type=channel" +
                "&maxResults=" + maxResults;
        ResponseEntity<ChannelSearch> response = restTemplate.exchange(
                uri, HttpMethod.GET,null, ChannelSearch.class);
        return response.getBody().getItems();
    }

    public Channel getChannel(String id){
        String uri = "https://www.googleapis.com/youtube/v3/search?" +
                //"key=AIzaSyAxabGmAkkFZ5ZvOUTr5pbzJxMrz5uNsKg" +
                "&part=snippet" +
                "&type=channel" +
                "&channelId=" + id;
        ResponseEntity<ChannelSearch> response = restTemplate.exchange(
                uri,HttpMethod.GET,null, ChannelSearch.class);
        return response.getBody().getItems().stream().findAny().orElse(null);
    }

}
