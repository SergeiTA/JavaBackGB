package homeWorks.models;


import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)


public class ResponseAccountInfo extends ResponseBase <ResponseAccountInfo.DataAccountInfo>{

    public ResponseAccountInfo() {

    }

    public ResponseAccountInfo(DataAccountInfo dataAccountInfo, Boolean success, Integer status) {
        super(dataAccountInfo, success, status);
    }


    @JsonPropertyOrder({
            "id",
            "url",
            "bio",
            "avatar",
            "avatar_name",
            "cover",
            "cover_name",
            "reputation",
            "reputation_name",
            "created",
            "pro_expiration",
            "user_follow",
            "is_blocked"
    })
    @Generated("jsonschema2pojo")
    public class DataAccountInfo {

        public DataAccountInfo() {

        }

        public DataAccountInfo(Integer id, String url, Object bio, String avatar, String avatarName, String cover,
                               String coverName, Integer reputation, String reputationName, Integer created,
                               Boolean proExpiration, UserFollow userFollow, Boolean isBlocked) {
            this.id = id;
            this.url = url;
            this.bio = bio;
            this.avatar = avatar;
            this.avatarName = avatarName;
            this.cover = cover;
            this.coverName = coverName;
            this.reputation = reputation;
            this.reputationName = reputationName;
            this.created = created;
            this.proExpiration = proExpiration;
            this.userFollow = userFollow;
            this.isBlocked = isBlocked;
        }

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("url")
        private String url;

        @JsonProperty("bio")
        private Object bio;

        @JsonProperty("avatar")
        private String avatar;

        @JsonProperty("avatar_name")
        private String avatarName;

        @JsonProperty("cover")
        private String cover;

        @JsonProperty("cover_name")
        private String coverName;

        @JsonProperty("reputation")
        private Integer reputation;

        @JsonProperty("reputation_name")
        private String reputationName;

        @JsonProperty("created")
        private Integer created;

        @JsonProperty("pro_expiration")
        private Boolean proExpiration;

        @JsonProperty("user_follow")
        private UserFollow userFollow;

        @JsonProperty("is_blocked")
        private Boolean isBlocked;

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("bio")
        public Object getBio() {
            return bio;
        }

        @JsonProperty("bio")
        public void setBio(Object bio) {
            this.bio = bio;
        }

        @JsonProperty("avatar")
        public String getAvatar() {
            return avatar;
        }

        @JsonProperty("avatar")
        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        @JsonProperty("avatar_name")
        public String getAvatarName() {
            return avatarName;
        }

        @JsonProperty("avatar_name")
        public void setAvatarName(String avatarName) {
            this.avatarName = avatarName;
        }

        @JsonProperty("cover")
        public String getCover() {
            return cover;
        }

        @JsonProperty("cover")
        public void setCover(String cover) {
            this.cover = cover;
        }

        @JsonProperty("cover_name")
        public String getCoverName() {
            return coverName;
        }

        @JsonProperty("cover_name")
        public void setCoverName(String coverName) {
            this.coverName = coverName;
        }

        @JsonProperty("reputation")
        public Integer getReputation() {
            return reputation;
        }

        @JsonProperty("reputation")
        public void setReputation(Integer reputation) {
            this.reputation = reputation;
        }

        @JsonProperty("reputation_name")
        public String getReputationName() {
            return reputationName;
        }

        @JsonProperty("reputation_name")
        public void setReputationName(String reputationName) {
            this.reputationName = reputationName;
        }

        @JsonProperty("created")
        public Integer getCreated() {
            return created;
        }

        @JsonProperty("created")
        public void setCreated(Integer created) {
            this.created = created;
        }

        @JsonProperty("pro_expiration")
        public Boolean getProExpiration() {
            return proExpiration;
        }

        @JsonProperty("pro_expiration")
        public void setProExpiration(Boolean proExpiration) {
            this.proExpiration = proExpiration;
        }

        @JsonProperty("user_follow")
        public UserFollow getUserFollow() {
            return userFollow;
        }

        @JsonProperty("user_follow")
        public void setUserFollow(UserFollow userFollow) {
            this.userFollow = userFollow;
        }

        @JsonProperty("is_blocked")
        public Boolean getIsBlocked() {
            return isBlocked;
        }

        @JsonProperty("is_blocked")
        public void setIsBlocked(Boolean isBlocked) {
            this.isBlocked = isBlocked;
        }

        @Override
        public String toString() {
            return "\nDataAccountInfo{" +
                    "id=" + id +
                    ", url='" + url + '\'' +
                    ", bio=" + bio +
                    ", avatar='" + avatar + '\'' +
                    ", avatarName='" + avatarName + '\'' +
                    ", cover='" + cover + '\'' +
                    ", coverName='" + coverName + '\'' +
                    ", reputation=" + reputation +
                    ", reputationName='" + reputationName + '\'' +
                    ", created=" + created +
                    ", proExpiration=" + proExpiration +
                    ", userFollow=" + userFollow +
                    ", isBlocked=" + isBlocked +
                    '}';
        }
    }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({
                "status"
        })
        @Generated("jsonschema2pojo")
        public static class UserFollow {


            @JsonProperty("status")
            private Boolean status;

            public UserFollow() {

            }

            public UserFollow(Boolean status) {
                this.status = status;
            }

            @JsonProperty("status")
            public Boolean getStatus() {
                return status;
            }

            @JsonProperty("status")
            public void setStatus(Boolean status) {
                this.status = status;
            }


            @Override
            public String toString() {
                return "\nUserFollow{" +
                        "status=" + status +
                        '}';
            }
        }

}
