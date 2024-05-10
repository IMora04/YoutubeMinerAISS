package aiss.youtubeMiner.controller;

import aiss.youtubeMiner.VMmodel.VMChannel;
import aiss.youtubeMiner.model.channel.Channel;
import aiss.youtubeMiner.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/youtubeminer/channels")
public class ChannelController {

    public static VMChannel channelParser(Channel channel) {
        VMChannel newChannel = new VMChannel();
        newChannel.setId(channel.getId().getId());
        newChannel.setDescription(channel.getSnippet().getDescription());
        newChannel.setName(channel.getSnippet().getTitle());
        newChannel.setCreatedTime(channel.getSnippet().getPublishedAt());
        return newChannel;
    }

    public VMChannel sendToVideoMiner(VMChannel newChannel) {
        String uri = "http://localhost:8080/videominer/channels";
        HttpEntity<VMChannel> request = new HttpEntity<>(newChannel);
        ResponseEntity<VMChannel> response = restTemplate.exchange(uri, HttpMethod.POST, request, VMChannel.class);
        return response.getBody();
    }

    @Autowired
    ChannelService service;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public List<Channel> findAll() {
        return service.getChannels();
    }

    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id){
        return service.getChannel(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void uploadChannels() {
        List<VMChannel> channels = service.getChannels().stream().map(ChannelController::channelParser).toList();
        System.out.println(channels);
        for(VMChannel v:channels) {
            sendToVideoMiner(v);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel uploadChannel(@PathVariable String id) {
        VMChannel newChannel = channelParser(findOne(id));
        return sendToVideoMiner(newChannel);
    }

}
