package com.example.photoalbum;
public interface onButtonPressListener
{
    //To handle next and prev button press events
    void onButtonPressed(Integer imageIndex);
    //For slide show
    void onCheckStatusChanged(Boolean check);
    //For gallery view
    void onGalleryCheckStatusChanged(Boolean check);

}
