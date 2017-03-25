package com.djw.retrofit_rxjava_mvp_dagger.data.zhihu;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/23.
 */

public class DaypaperData {

    /**
     * date : 20170323
     * stories : [{"images":["https://pic4.zhimg.com/v2-af7f0631793dc3e1c238c51e44357347.jpg"],"type":0,"id":9308428,"ga_prefix":"032312","title":"《四重奏》完结，轻井泽的故事才刚刚开始"},{"images":["https://pic1.zhimg.com/v2-54861132142b30aba4d616cd0c2d4c04.jpg"],"type":0,"id":9309218,"ga_prefix":"032312","title":"大误 · 传统不能丢"},{"images":["https://pic1.zhimg.com/v2-1beb995f722d60104c080ebb941e54bc.jpg"],"type":0,"id":9308052,"ga_prefix":"032310","title":"没听说过白海松贝？这不就是长相放浪的象拔蚌嘛"},{"images":["https://pic3.zhimg.com/v2-f43b6cf47a6c3014675815c55c26f7c2.jpg"],"type":0,"id":9308388,"ga_prefix":"032309","title":"你爱打的那些游戏，（暂时）可以分成这几类"},{"images":["https://pic1.zhimg.com/v2-8fb6846a0858e669e944b6bd96565d10.jpg"],"type":0,"id":9308060,"ga_prefix":"032308","title":"未来可以实现用机器研究历史吗？"},{"images":["https://pic3.zhimg.com/v2-450aa598449ff81fffd26cdc761935de.jpg"],"type":0,"id":9308056,"ga_prefix":"032307","title":"收购大麦网，马云继续追逐着他建立「大文娱板块」的梦想"},{"images":["https://pic3.zhimg.com/v2-96fa55628af9eeed3aaf6cc3ef7b008a.jpg"],"type":0,"id":9263659,"ga_prefix":"032307","title":"在婚姻被埋怨淹没前，我们还能做些什么？"},{"images":["https://pic4.zhimg.com/v2-79e9b910762dc84a066c78bfe4ca4b33.jpg"],"type":0,"id":9308285,"ga_prefix":"032307","title":"谷歌发布 Android O 预览版，卡顿、耗电、乱通知可能有救了"},{"images":["https://pic2.zhimg.com/v2-0c9a4d93684b11220a576d30908074a9.jpg"],"type":0,"id":9304045,"ga_prefix":"032306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-2c6d983b70a1d677106d06cd30ec6120.jpg","type":0,"id":9308056,"ga_prefix":"032307","title":"收购大麦网，马云继续追逐着他建立「大文娱板块」的梦想"},{"image":"https://pic4.zhimg.com/v2-c9e12642ad26c6a63e965015eaa0cd93.jpg","type":0,"id":9308285,"ga_prefix":"032307","title":"谷歌发布 Android O 预览版，卡顿、耗电、乱通知可能有救了"},{"image":"https://pic2.zhimg.com/v2-7f846500bca3f4f3641f57c7270a93b9.jpg","type":0,"id":9307467,"ga_prefix":"032218","title":"连片名都一样，《疯狂动物城》是抄袭的吗？"},{"image":"https://pic2.zhimg.com/v2-b74b55c0787c975cf08270f746ddac85.jpg","type":0,"id":9306667,"ga_prefix":"032212","title":"苹果发布了红色的 iPhone 7，和更便宜的 iPad"},{"image":"https://pic2.zhimg.com/v2-1c22882aabe23e47ec82b212281331e5.jpg","type":0,"id":9304650,"ga_prefix":"032209","title":"腌笃鲜，属于春天的鲜味"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-af7f0631793dc3e1c238c51e44357347.jpg"]
         * type : 0
         * id : 9308428
         * ga_prefix : 032312
         * title : 《四重奏》完结，轻井泽的故事才刚刚开始
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", images=" + images +
                    '}';
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-2c6d983b70a1d677106d06cd30ec6120.jpg
         * type : 0
         * id : 9308056
         * ga_prefix : 032307
         * title : 收购大麦网，马云继续追逐着他建立「大文娱板块」的梦想
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "image='" + image + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DaypaperData{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
