package com.example.user.shoplocal1;

import java.io.Serializable;

/**
 * Created by user on 5/23/2016.
 */
public class Entity implements Serializable {
    private String id="";

    private String name="";

    private String price="";

    private String description="";

    private String image="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
