package aiss.youtubeMiner.parser;

import aiss.youtubeMiner.VMmodel.*;
import aiss.youtubeMiner.model.caption.Caption;
import aiss.youtubeMiner.model.channel.Channel;
import aiss.youtubeMiner.model.comment.Comment;
import aiss.youtubeMiner.model.comment.CommentSnippet__1;
import aiss.youtubeMiner.model.videoSnippet.VideoSnippet;

public class ModelParser {

    public static VMChannel channelParser(Channel channel) {
        VMChannel newChannel = new VMChannel();



        newChannel.setId(channel.getId().getId());



        newChannel.setDescription(channel.getSnippet().getDescription());
        newChannel.setName(channel.getSnippet().getTitle());
        newChannel.setCreatedTime(channel.getSnippet().getPublishedAt());



        newChannel.setVideos(channel.getVideos().stream().map(ModelParser::videoParser).toList());



        return newChannel;
    }

    public static VMVideo videoParser(VideoSnippet video) {
        VMVideo newVideo = new VMVideo();
        newVideo.setId(video.getId().getVideoId());
        newVideo.setComments(video.getComments().stream().map(ModelParser::commentParser).toList());
        newVideo.setName(video.getSnippet().getTitle());
        newVideo.setReleaseTime(video.getSnippet().getPublishedAt());
        newVideo.setDescription(video.getSnippet().getDescription());
        newVideo.setCaptions(video.getCaptions().stream().map(ModelParser::captionParser).toList());
        return newVideo;
    }

    public static VMComment commentParser(Comment comment) {
        VMComment newComment = new VMComment();
        newComment.setAuthor(userParserFromComment(comment.getCommentSnippet().getTopLevelComment().getSnippet()));
        newComment.setId(comment.getCommentSnippet().getTopLevelComment().getId());
        newComment.setText(comment.getCommentSnippet().getTopLevelComment().getSnippet().getTextOriginal());
        newComment.setCreatedOn(comment.getCommentSnippet().getTopLevelComment().getSnippet().getPublishedAt());
        return newComment;
    }

    public static VMCaption captionParser(Caption caption) {
        VMCaption newCaption = new VMCaption();
        newCaption.setId(caption.getId());
        newCaption.setName(caption.getSnippet().getName());
        newCaption.setLanguage(caption.getSnippet().getLanguage());
        return newCaption;
    }

    public static VMUser userParserFromComment(CommentSnippet__1 comment) {
        VMUser newUser = new VMUser();
        newUser.setName(comment.getAuthorDisplayName());
        newUser.setUser_link(comment.getAuthorChannelUrl());
        newUser.setPicture_link(comment.getAuthorProfileImageUrl());
        return newUser;
    }

}
