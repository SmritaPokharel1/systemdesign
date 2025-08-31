package com.example.tagging;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private String name;
    private long id;
    private List<Tag> tags;

    public Entity(long id, String name){

        this.name = name;
        this.id = id;
        this.tags = new ArrayList<>();
    }

    public void addTag(Tag tag){

        this.tags.add(tag);
    }
}
