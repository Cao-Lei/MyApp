package com.bwie.caolei.myapp.ui.model;

import java.util.List;

/**
 * 1.
 * 2.曹磊
 * 3.2016/11/23
 */
public class FragmentTwoRecyclerBean {
    /**
     * description : 看别人的经历，理解自己的生活
     * id : 1
     * name : 深夜惊奇
     * thumbnail : http://pic3.zhimg.com/91125c9aebcab1c84f58ce4f8779551e.jpg
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String description;
        private int id;
        private String name;
        private String thumbnail;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
/*
    private int imgid;
    private String content;
    private String title;

    public FragmentTwoRecyclerBean(int imgid, String content, String title) {
        this.imgid = imgid;
        this.content = content;
        this.title = title;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/


}
