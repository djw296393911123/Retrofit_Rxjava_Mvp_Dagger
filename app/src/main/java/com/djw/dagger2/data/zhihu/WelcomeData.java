package com.djw.dagger2.data.zhihu;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/26.
 */

public class WelcomeData {


    private List<CreativesBean> creatives;

    public List<CreativesBean> getCreatives() {
        return creatives;
    }

    public void setCreatives(List<CreativesBean> creatives) {
        this.creatives = creatives;
    }

    public static class CreativesBean {
        /**
         * url : https://pic4.zhimg.com/v2-d0837fd8e39d98b2d58d1911e4fbd913.jpg
         * start_time : 1493178601
         * impression_tracks : ["https://sugar.zhihu.com/track?vs=1&ai=3956&ut=&cg=2&ts=1493178601.06&si=ff96ae3b72324fd09a8b5773936b1f4b&lu=0&hn=ad-engine.ad-engine.5aa533ca&at=impression&pf=PC&az=11&sg=b87aee799255cf440d51f418a1f9d3bf"]
         * type : 0
         * id : 3956
         */

        private String url;
        private int start_time;
        private int type;
        private String id;
        private List<String> impression_tracks;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getImpression_tracks() {
            return impression_tracks;
        }

        public void setImpression_tracks(List<String> impression_tracks) {
            this.impression_tracks = impression_tracks;
        }
    }
}
