package homeWorks.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "datetime",
        "type",
        "animated",
        "width",
        "height",
        "size",
        "views",
        "bandwidth",
        "vote",
        "favorite",
        "nsfw",
        "section",
        "account_url",
        "account_id",
        "is_ad",
        "in_most_viral",
        "has_sound",
        "tags",
        "ad_type",
        "ad_url",
        "edited",
        "in_gallery",
        "deletehash",
        "name",
        "link"
})
@Generated("jsonschema2pojo")

public class ResponseImage extends ResponseBase <ResponseImage.DataImage> {

    public ResponseImage() {

    }

    public ResponseImage(DataImage dataImage, Boolean success, Integer status) {
        super(dataImage, success, status);
    }

    public class DataImage {

        @JsonProperty("id")
        private String id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("description")
        private Object description;

        @JsonProperty("datetime")
        private Integer datetime;

        @JsonProperty("type")
        private String type;

        @JsonProperty("animated")
        private Boolean animated;

        @JsonProperty("width")
        private Integer width;

        @JsonProperty("height")
        private Integer height;

        @JsonProperty("size")
        private Integer size;

        @JsonProperty("views")
        private Integer views;

        @JsonProperty("bandwidth")
        private Integer bandwidth;

        @JsonProperty("vote")
        private Object vote;

        @JsonProperty("favorite")
        private Boolean favorite;

        @JsonProperty("nsfw")
        private Object nsfw;

        @JsonProperty("section")
        private Object section;

        @JsonProperty("account_url")
        private Object accountUrl;

        @JsonProperty("account_id")
        private Integer accountId;

        @JsonProperty("is_ad")
        private Boolean isAd;

        @JsonProperty("in_most_viral")
        private Boolean inMostViral;

        @JsonProperty("has_sound")
        private Boolean hasSound;

        @JsonProperty("tags")
        private List<Object> tags = new ArrayList<Object>();

        @JsonProperty("ad_type")
        private Integer adType;

        @JsonProperty("ad_url")
        private String adUrl;

        @JsonProperty("edited")
        private String edited;

        @JsonProperty("in_gallery")
        private Boolean inGallery;

        @JsonProperty("deletehash")
        private String deletehash;

        @JsonProperty("name")
        private String name;

        @JsonProperty("link")
        private String link;

        public DataImage() {

        }

        public DataImage(String id, String title, Object description, Integer datetime, String type, Boolean animated,
                         Integer width, Integer height, Integer size, Integer views, Integer bandwidth, Object vote,
                         Boolean favorite, Object nsfw, Object section, Object accountUrl, Integer accountId,
                         Boolean isAd, Boolean inMostViral, Boolean hasSound, List<Object> tags, Integer adType,
                         String adUrl, String edited, Boolean inGallery, String deletehash, String name, String link) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.datetime = datetime;
            this.type = type;
            this.animated = animated;
            this.width = width;
            this.height = height;
            this.size = size;
            this.views = views;
            this.bandwidth = bandwidth;
            this.vote = vote;
            this.favorite = favorite;
            this.nsfw = nsfw;
            this.section = section;
            this.accountUrl = accountUrl;
            this.accountId = accountId;
            this.isAd = isAd;
            this.inMostViral = inMostViral;
            this.hasSound = hasSound;
            this.tags = tags;
            this.adType = adType;
            this.adUrl = adUrl;
            this.edited = edited;
            this.inGallery = inGallery;
            this.deletehash = deletehash;
            this.name = name;
            this.link = link;
        }

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("title")
        public void setTitle(String title) {
            this.title = title;
        }

        @JsonProperty("description")
        public Object getDescription() {
            return description;
        }

        @JsonProperty("description")
        public void setDescription(Object description) {
            this.description = description;
        }

        @JsonProperty("datetime")
        public Integer getDatetime() {
            return datetime;
        }

        @JsonProperty("datetime")
        public void setDatetime(Integer datetime) {
            this.datetime = datetime;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("animated")
        public Boolean getAnimated() {
            return animated;
        }

        @JsonProperty("animated")
        public void setAnimated(Boolean animated) {
            this.animated = animated;
        }

        @JsonProperty("width")
        public Integer getWidth() {
            return width;
        }

        @JsonProperty("width")
        public void setWidth(Integer width) {
            this.width = width;
        }

        @JsonProperty("height")
        public Integer getHeight() {
            return height;
        }

        @JsonProperty("height")
        public void setHeight(Integer height) {
            this.height = height;
        }

        @JsonProperty("size")
        public Integer getSize() {
            return size;
        }

        @JsonProperty("size")
        public void setSize(Integer size) {
            this.size = size;
        }

        @JsonProperty("views")
        public Integer getViews() {
            return views;
        }

        @JsonProperty("views")
        public void setViews(Integer views) {
            this.views = views;
        }

        @JsonProperty("bandwidth")
        public Integer getBandwidth() {
            return bandwidth;
        }

        @JsonProperty("bandwidth")
        public void setBandwidth(Integer bandwidth) {
            this.bandwidth = bandwidth;
        }

        @JsonProperty("vote")
        public Object getVote() {
            return vote;
        }

        @JsonProperty("vote")
        public void setVote(Object vote) {
            this.vote = vote;
        }

        @JsonProperty("favorite")
        public Boolean getFavorite() {
            return favorite;
        }

        @JsonProperty("favorite")
        public void setFavorite(Boolean favorite) {
            this.favorite = favorite;
        }

        @JsonProperty("nsfw")
        public Object getNsfw() {
            return nsfw;
        }

        @JsonProperty("nsfw")
        public void setNsfw(Object nsfw) {
            this.nsfw = nsfw;
        }

        @JsonProperty("section")
        public Object getSection() {
            return section;
        }

        @JsonProperty("section")
        public void setSection(Object section) {
            this.section = section;
        }

        @JsonProperty("account_url")
        public Object getAccountUrl() {
            return accountUrl;
        }

        @JsonProperty("account_url")
        public void setAccountUrl(Object accountUrl) {
            this.accountUrl = accountUrl;
        }

        @JsonProperty("account_id")
        public Integer getAccountId() {
            return accountId;
        }

        @JsonProperty("account_id")
        public void setAccountId(Integer accountId) {
            this.accountId = accountId;
        }

        @JsonProperty("is_ad")
        public Boolean getIsAd() {
            return isAd;
        }

        @JsonProperty("is_ad")
        public void setIsAd(Boolean isAd) {
            this.isAd = isAd;
        }

        @JsonProperty("in_most_viral")
        public Boolean getInMostViral() {
            return inMostViral;
        }

        @JsonProperty("in_most_viral")
        public void setInMostViral(Boolean inMostViral) {
            this.inMostViral = inMostViral;
        }

        @JsonProperty("has_sound")
        public Boolean getHasSound() {
            return hasSound;
        }

        @JsonProperty("has_sound")
        public void setHasSound(Boolean hasSound) {
            this.hasSound = hasSound;
        }

        @JsonProperty("tags")
        public List<Object> getTags() {
            return tags;
        }

        @JsonProperty("tags")
        public void setTags(List<Object> tags) {
            this.tags = tags;
        }

        @JsonProperty("ad_type")
        public Integer getAdType() {
            return adType;
        }

        @JsonProperty("ad_type")
        public void setAdType(Integer adType) {
            this.adType = adType;
        }

        @JsonProperty("ad_url")
        public String getAdUrl() {
            return adUrl;
        }

        @JsonProperty("ad_url")
        public void setAdUrl(String adUrl) {
            this.adUrl = adUrl;
        }

        @JsonProperty("edited")
        public String getEdited() {
            return edited;
        }

        @JsonProperty("edited")
        public void setEdited(String edited) {
            this.edited = edited;
        }

        @JsonProperty("in_gallery")
        public Boolean getInGallery() {
            return inGallery;
        }

        @JsonProperty("in_gallery")
        public void setInGallery(Boolean inGallery) {
            this.inGallery = inGallery;
        }

        @JsonProperty("deletehash")
        public String getDeletehash() {
            return deletehash;
        }

        @JsonProperty("deletehash")
        public void setDeletehash(String deletehash) {
            this.deletehash = deletehash;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("link")
        public String getLink() {
            return link;
        }

        @JsonProperty("link")
        public void setLink(String link) {
            this.link = link;
        }

        @Override
        public String toString() {
            return "DataImage{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", description=" + description +
                    ", datetime=" + datetime +
                    ", type='" + type + '\'' +
                    ", animated=" + animated +
                    ", width=" + width +
                    ", height=" + height +
                    ", size=" + size +
                    ", views=" + views +
                    ", bandwidth=" + bandwidth +
                    ", vote=" + vote +
                    ", favorite=" + favorite +
                    ", nsfw=" + nsfw +
                    ", section=" + section +
                    ", accountUrl=" + accountUrl +
                    ", accountId=" + accountId +
                    ", isAd=" + isAd +
                    ", inMostViral=" + inMostViral +
                    ", hasSound=" + hasSound +
                    ", tags=" + tags +
                    ", adType=" + adType +
                    ", adUrl='" + adUrl + '\'' +
                    ", edited='" + edited + '\'' +
                    ", inGallery=" + inGallery +
                    ", deletehash='" + deletehash + '\'' +
                    ", name='" + name + '\'' +
                    ", link='" + link + '\'' +
                    '}';
        }
    }
}
