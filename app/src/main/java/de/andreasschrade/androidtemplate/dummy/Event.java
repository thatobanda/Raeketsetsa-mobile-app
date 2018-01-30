package de.andreasschrade.androidtemplate.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by geekulcha on 10/17/2017.
 */

public class Event {
 public String title,author,content,content2,photo,id;

    public static final List<Event> ITEMS = new ArrayList<>();

    public static final Map<String, Event> ITEM_MAP = new HashMap<>();


    public static void addItem(Event item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);

    }


    public Event() {
        this.id = "";
        this.photo= "";
        this.content2="";
        this.title = "";
        this.author = "";
        this.content = "";

    }

    public Event(String title, String author, String content,String content2, String photo, String id) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.photo = photo;
        this.id = id;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo= photo;
    }
}
