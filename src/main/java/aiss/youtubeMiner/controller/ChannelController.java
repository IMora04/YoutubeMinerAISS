package aiss.youtubeMiner.controller;

import aiss.youtubeMiner.model.channel.Channel;
import aiss.youtubeMiner.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/youtubeminer/channels")
public class ChannelController {

    @Autowired
    ChannelService service;

    @GetMapping
    public List<Channel> findAll(){
        return service.getChannels();
    }

    @GetMapping("/{id}")
    public Channel findOneChannel(@PathVariable String id){
        return service.getChannel(id);
    }


}
