package com.widle.coinscap.Model;

import android.media.MediaDescription;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by imperial-web on 4/18/2018.
 */

public class PodcastChanel{
    public String channel_id = "";
    public String show_id = "";
    public String title = "";
    public String description = "";
    public String subtitle = "";
    public String rating = "";
    public String votes = "";
    public String channel_type = "";
    public String media_link = "";
    public String pod_link = "";
    public String type = "";
    public String mime_type = "";
    public String image = "";
    public String relevance = "";


//    public PodcastChanel(Parcel in) {
//        channel_id = in.readString();
//        show_id = in.readString();
//        title = in.readString();
//        description = in.readString();
//        subtitle = in.readString();
//        rating = in.readString();
//        votes = in.readString();
//        channel_type = in.readString();
//        media_link = in.readString();
//        pod_link = in.readString();
//        type = in.readString();
//        mime_type = in.readString();
//        image = in.readString();
//        relevance = in.readString();
//    }
//
//    private PodcastChanel(
//            final String channel_id,
//            final String show_id,
//            final String title,
//            final String description,
//            final String subtitle,
//            final String rating,
//            final String votes,
//            final String channel_type,
//            final String media_link,
//            final String pod_link,
//            final String type,
//            final String mime_type,
//            final String image,
//            final String relevance) {
//        this.channel_id = channel_id;
//        this.show_id = show_id;
//        this.title = title;
//        this.description = description;
//        this.subtitle = subtitle;
//        this.rating = rating;
//        this.votes = votes;
//        this.channel_type = channel_type;
//        this.media_link = media_link;
//        this.pod_link = pod_link;
//        this.type = type;
//        this.mime_type = mime_type;
//        this.image = image;
//        this.relevance = relevance;
//
//
//    }
//
//
//    public static final Creator<PodcastChanel> CREATOR = new Creator<PodcastChanel>() {
//        @Override
//        public PodcastChanel createFromParcel(Parcel in) {
//            return new PodcastChanel(in);
//        }
//
//        @Override
//        public PodcastChanel[] newArray(int size) {
//            return new PodcastChanel[size];
//        }
//    };
//
//    public PodcastChanel() {
//
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(channel_id);
//        parcel.writeString(show_id);
//        parcel.writeString(title);
//        parcel.writeString(description);
//        parcel.writeString(subtitle);
//        parcel.writeString(rating);
//        parcel.writeString(votes);
//        parcel.writeString(channel_type);
//        parcel.writeString(media_link);
//        parcel.writeString(pod_link);
//        parcel.writeString(type);
//        parcel.writeString(mime_type);
//        parcel.writeString(image);
//        parcel.writeString(relevance);
//    }
//
//
//
//    // Builder for Video object.
//    public static class VideoBuilder {
//        private String channel_id;
//        private String show_id;
//        private String title;
//        private String description;
//        private String subtitle;
//        private String rating;
//        private String votes;
//        private String channel_type;
//        private String media_link;
//        private String pod_link;
//        private String type;
//        private String mime_type;
//        private String image;
//        private String relevance;
//
//        public VideoBuilder channel_id(String channel_id) {
//            this.channel_id = channel_id;
//            return this;
//        }
//        public VideoBuilder show_id(String show_id) {
//            this.show_id = show_id;
//            return this;
//        }
//        public VideoBuilder title(String title) {
//            this.title = title;
//            return this;
//        }
//        public VideoBuilder description(String description) {
//            this.description = description;
//            return this;
//        }
//        public VideoBuilder subtitle(String subtitle) {
//            this.subtitle = subtitle;
//            return this;
//        }
//
//        public VideoBuilder rating(String rating) {
//            this.rating = rating;
//            return this;
//        }
//
//        public VideoBuilder votes(String votes) {
//            this.votes = votes;
//            return this;
//        }
//
//        public VideoBuilder channel_type(String channel_type) {
//            this.channel_type = channel_type;
//            return this;
//        }
//
//        public VideoBuilder media_link(String media_link) {
//            this.media_link = media_link;
//            return this;
//        }
//
//        public VideoBuilder pod_link(String pod_link) {
//            this.pod_link = pod_link;
//            return this;
//        }
//
//        public VideoBuilder type(String type) {
//            this.type = type;
//            return this;
//        }
//
//        public VideoBuilder mime_type(String mime_type) {
//            this.mime_type = mime_type;
//            return this;
//        }
//
//        public VideoBuilder image(String image) {
//            this.image = image;
//            return this;
//        }
//
//        public VideoBuilder relevance(String relevance) {
//            this.relevance = relevance;
//            return this;
//        }
//
////        public PodcastChanel buildFromMediaDesc(MediaDescription desc) {
////            return new PodcastChanel(
////                    Long.parseLong(desc.getMediaId()),
////                    "", // Category - not provided by MediaDescription.
////                    String.valueOf(desc.getTitle()),
////                    String.valueOf(desc.getDescription()),
////                    "", // Media URI - not provided by MediaDescription.
////                    "", // Background Image URI - not provided by MediaDescription.
////                    String.valueOf(desc.getIconUri()),
////                    String.valueOf(desc.getSubtitle())
////            );
////        }
//
//        public PodcastChanel build() {
//            return new PodcastChanel(
//                    channel_id,
//                    show_id,
//                    title,
//                    description,
//                    subtitle,
//                    rating,
//                    votes,
//                    channel_type,
//                    media_link,
//                    pod_link,
//                    type,
//                    mime_type,
//                    image,
//                    relevance
//            );
//        }
//    }
}
