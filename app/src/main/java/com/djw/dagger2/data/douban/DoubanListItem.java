package com.djw.dagger2.data.douban;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/7.
 */

public class DoubanListItem {

    /**
     * rating : {"max":10,"numRaters":1074,"average":"7.6","min":0}
     * subtitle : 为何普世商业价值在中国行不通
     * author : ["申音"]
     * pubdate : 2011-7-1
     * tags : [{"count":1126,"name":"商业","title":"商业"},{"count":489,"name":"创业","title":"创业"},{"count":308,"name":"商业的常识","title":"商业的常识"},{"count":267,"name":"管理","title":"管理"},{"count":207,"name":"经济","title":"经济"},{"count":191,"name":"申音","title":"申音"},{"count":183,"name":"互联网","title":"互联网"},{"count":104,"name":"经济学","title":"经济学"}]
     * origin_title :
     * image : https://img1.doubanio.com/mpic/s6569607.jpg
     * binding : 平装
     * translator : []
     * catalog : 序言
     商业的真问题        李开复
     你看风景，风景里的人也在看你    牛文文
     第一章 山寨之国的丛林现实
     美国没有史玉柱，中国没有乔布斯
     创新的园艺学
     中国商业的黑暗原力
     第二章 从零起步
     船长
     W和L
     给海归技术创业兄弟的九个忠告
     当创业遇上黑天鹅
     第三章 商业模式的迷思
     别把商业模式当成“葵花宝典”
     关于商业模式的那些迷思
     * pages : 262
     * images : {"small":"https://img1.doubanio.com/spic/s6569607.jpg","large":"https://img1.doubanio.com/lpic/s6569607.jpg","medium":"https://img1.doubanio.com/mpic/s6569607.jpg"}
     * alt : https://book.douban.com/subject/6548683/
     * id : 6548683
     * publisher : 山西经济出版社
     * isbn10 : 7807674032
     * isbn13 : 9787807674030
     * title : 商业的常识
     * url : https://api.douban.com/v2/book/6548683
     * alt_title :
     * author_intro : NTA创新传播机构创始人，社会化媒体营销探路人。
     《创业家》杂志联合创始人，前执行主编。
     曾服务于《中国青年报》、《环球企业家》、《中国企业家》等多家知名媒体，现为《周末画报》、《南都周刊》等多家媒体专栏作家，并担任央视《对话》、《赢在中国》、《创新无限》、第一财经《中国经营者》等电视栏目的特约策划。
     长期研究国内外创新商业模式，关注中国企业成长和风险投资，与许多国内年轻一代创业家、VC投资人有密切交流
     * summary : ★为什么美国没有史玉柱，中国没有乔布斯？
     ★什么是“对的行业”、“错的行业”？
     ★我们需要什么样的营销？
     ★老板为什么要读商学院？
     ★山寨公司还需要管理吗？
     ★资源问题是个“伪问题”？
     ★别把商业模式当成葵花宝典
     ★给海归技术创业兄弟的九个忠告
     ★在一个不伟大的行业里，做一个伟大的公司
     ★是什么让互联网遭遇了有史以来最鸡犬不宁的一战？
     * price : 35.00元
     */

    private RatingBean rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;
    private List<String> author;
    private List<TagsBean> tags;
    private List<?> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<?> getTranslator() {
        return translator;
    }

    public void setTranslator(List<?> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 1074
         * average : 7.6
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img1.doubanio.com/spic/s6569607.jpg
         * large : https://img1.doubanio.com/lpic/s6569607.jpg
         * medium : https://img1.doubanio.com/mpic/s6569607.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class TagsBean {
        /**
         * count : 1126
         * name : 商业
         * title : 商业
         */

        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TagsBean{" +
                    "count=" + count +
                    ", name='" + name + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DoubanListItem{" +
                "rating=" + rating +
                ", subtitle='" + subtitle + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", origin_title='" + origin_title + '\'' +
                ", image='" + image + '\'' +
                ", binding='" + binding + '\'' +
                ", catalog='" + catalog + '\'' +
                ", pages='" + pages + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", publisher='" + publisher + '\'' +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", alt_title='" + alt_title + '\'' +
                ", author_intro='" + author_intro + '\'' +
                ", summary='" + summary + '\'' +
                ", price='" + price + '\'' +
                ", author=" + author +
                ", tags=" + tags +
                ", translator=" + translator +
                '}';
    }
}
