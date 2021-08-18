package ltd.icecold.orange.bean;

import com.google.gson.annotations.JsonAdapter;
import ltd.icecold.orange.factory.StageModelForeignKeyTypeAdapterFactory;

import java.util.List;

public class StageBean {
    private String name;
    private String video;
    private boolean canClose;
    private List<DramaListDTO> dramaList;
    private List<VoteDTO> vote;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<DramaListDTO> getDramaList() {
        return dramaList;
    }

    public void setDramaList(List<DramaListDTO> dramaList) {
        this.dramaList = dramaList;
    }

    public List<VoteDTO> getVote() {
        return vote;
    }

    public void setVote(List<VoteDTO> vote) {
        this.vote = vote;
    }

    public boolean isCanClose() {
        return canClose;
    }

    public void setCanClose(boolean canClose) {
        this.canClose = canClose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class DramaListDTO {
        private String time;
        private List<String> buttons;
        private List<String> texts;
        private List<String> images;
        private String status;
        private Integer last;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<String> getButtons() {
            return buttons;
        }

        public void setButtons(List<String> buttons) {
            this.buttons = buttons;
        }

        public List<String> getTexts() {
            return texts;
        }

        public void setTexts(List<String> texts) {
            this.texts = texts;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getLast() {
            return last;
        }

        public void setLast(Integer last) {
            this.last = last;
        }
    }

    public static class VoteDTO {
        private String time;
        private String title;
        private List<String> content;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
    }
}
