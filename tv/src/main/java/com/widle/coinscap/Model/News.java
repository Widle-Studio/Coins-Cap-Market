package com.widle.coinscap.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imperial-web on 3/29/2018.
 */



    public class News implements Serializable {
        public String id = "";
        public String guid = "";
        public String published_on = "";
        public String imageurl = "";
        public String title = "";
        public String url = "";
        public String source = "";
        public String body = "";
        public String tags = "";
        public String lang = "";


        public List<News> list;

    }

