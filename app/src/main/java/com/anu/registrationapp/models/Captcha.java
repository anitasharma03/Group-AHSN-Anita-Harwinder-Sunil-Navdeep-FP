package com.anu.registrationapp.models;

public class Captcha {

    private int imageId;
    private boolean isTrafficLight;
    private boolean isSelected;

    public Captcha(int imageId, boolean isTrafficLight, boolean isSelected) {
        this.imageId = imageId;
        this.isTrafficLight = isTrafficLight;
        this.isSelected = isSelected;
    }

    public Captcha() {
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean isTrafficLight() {
        return isTrafficLight;
    }

    public void setTrafficLight(boolean trafficLight) {
        isTrafficLight = trafficLight;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
