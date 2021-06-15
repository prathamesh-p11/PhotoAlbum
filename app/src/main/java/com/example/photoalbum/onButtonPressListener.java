package com.example.photoalbum;

public interface onButtonPressListener
{
    void onButtonPressed(Integer imageIndex);
    //For slide show
    void onCheckStatusChanged(Boolean check);
    void onGalleryCheckStatusChanged(Boolean check);

}
