package aiss.youtubeMiner.controller;

import aiss.youtubeMiner.VMmodel.VMChannel;
import aiss.youtubeMiner.model.channel.Channel;
import aiss.youtubeMiner.service.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Tag(name = "YoutubeMiner", description = "API methods and models documentation")
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

    @Operation( summary = "Retrieve all channels",
            description = "Get every channel",
            tags = { "channels", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Channel list",
                    content = { @Content(schema = @Schema(implementation = Channel.class),
                    mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description="No channels retrieved",
                    content = { @Content(schema = @Schema()) })})
    @GetMapping
    public List<Channel> findAll() {
        return service.getChannels();
    }

    @Operation(summary = "Retrieve channel by Id",
            description = "Get the channel specified with Id",
            tags = { "channels", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Get one specified channel",
            content = { @Content(schema = @Schema(implementation = Channel.class),
                    mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", description="Channel not found",
                content = { @Content(schema = @Schema()) })})


    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id){
        return service.getChannel(id);
    }

    @Operation(summary = "Upload a list of channels to videominer",
            description = "Upload a list of channels",
            tags = { "channels", "upload" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Upload a list of channels",
                    content = { @Content(schema = @Schema(implementation = Channel.class),
                            mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", description="Cannot upload",
            content = { @Content(schema = @Schema()) })})

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void uploadChannels() {
        List<VMChannel> channels = service.getChannels().stream().map(ChannelController::channelParser).toList();
        System.out.println(channels);
        for(VMChannel v:channels) {
            sendToVideoMiner(v);
        }
    }

    @Operation(summary = "upload a channel to videominer",
            description = "Upload a new channel",
            tags = { "channels", "upload" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Upload an specified channel",
                    content = { @Content(schema = @Schema(implementation = Channel.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description="Channel not found",
                    content = { @Content(schema = @Schema()) })})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel uploadChannel(@PathVariable String id) {
        VMChannel newChannel = channelParser(findOne(id));
        return sendToVideoMiner(newChannel);
    }

}
