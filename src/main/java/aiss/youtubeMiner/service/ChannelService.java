package aiss.youtubeMiner.service;

import aiss.youtubeMiner.model.channel.Channel;
import aiss.youtubeMiner.model.channel.ChannelSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    ResponseEntity<ChannelSearch> response;
    public List<Channel> getChannels(){
        String uri = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyBJcbUFSXcmamEuytMYVsiVPvdbdQkG6i4&part=snippet&type=channel";
        response = restTemplate.exchange(uri, HttpMethod.GET,
                null, ChannelSearch.class);

        return response.getBody().getItems();
    }

    public Channel getChannel(String id){
        String uri = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyBJcbUFSXcmamEuytMYVsiVPvdbdQkG6i4&part=snippet&type=channel&channelId="
                + id;
        response = restTemplate.exchange(uri,HttpMethod.GET,null,ChannelSearch.class);

        return response.getBody().getItems().get(0);
    }

}
