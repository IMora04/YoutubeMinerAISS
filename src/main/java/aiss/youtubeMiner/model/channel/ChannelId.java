
package aiss.youtubeMiner.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelId {

    @JsonProperty("channelId")
    private String channelId;

    @JsonProperty("channelId")
    public String getId() {
        return channelId;
    }

    @JsonProperty("channelId")
    public void setId(String id) {
        this.channelId = id;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ChannelId.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("channelId");
        sb.append('=');
        sb.append(((this.channelId == null)?"<null>":this.channelId));
        return sb.toString();
    }

}
