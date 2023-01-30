package com.xyz.newsh.domain;

import java.util.List;


public class NewsCenterBean {


    private List<Integer> extend;
    private List<DataEntity> data;
    private int retcode;

    public class Children
    {
        private int id;

        private String title;

        private int type;

        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


    public class DataEntity
    {
        private Integer id;

        private String title;

        private Integer type;

        private List<Children> children;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setType(int type){
            this.type = type;
        }
        public int getType(){
            return this.type;
        }
        public void setChildren(List<Children> children){
            this.children = children;
        }
        public List<Children> getChildren(){
            return this.children;
        }
    }


    public List<Integer> getExtend() {
        return extend;
    }

    public void setExtend(List<Integer> extend) {
        this.extend = extend;
    }

    public List<DataEntity> getDataEntity() {
        return data;
    }

    public void setDataEntity(List<DataEntity> DataEntity) {
        this.data = data;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    @Override
    public String toString() {
        return "NewsCenterBean{" +
                "extend=" + extend +
                ", data=" + data +
                ", retcode=" + retcode +
                '}';
    }
}
