package com.example.photoalbum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements onButtonPressListener
{
    ImageFragment imageFragment;
    OptionsFragment optionsFragment;
    GalleryFragment galleryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageFragment =new ImageFragment();
        optionsFragment = new OptionsFragment();
        galleryFragment = new GalleryFragment();

        Bundle bundle  = new Bundle();
        bundle.putInt("Index", 0);
        imageFragment.setArguments(bundle);
        FragmentTransaction fragTransaction =getSupportFragmentManager().beginTransaction();
        fragTransaction.add(R.id.imageFragContainerView, imageFragment);
        fragTransaction.commit();
    }

    @Override
    public void onButtonPressed(Integer index)
    {
        //Get the index from option fragment (decrement or increment)
        //Pass it to image fragment to display corresponding image stored in array
        ImageFragment imgFrag = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Index", index);
        imgFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.imageFragContainerView, imgFrag).commit();
    }

    @Override
    public void onCheckStatusChanged(Boolean check)
    {
        ImageFragment slideFrag;
        slideFrag = (ImageFragment) getSupportFragmentManager().findFragmentById(R.id.imageFragContainerView);
        
        if(slideFrag!=null)
        {
            slideFrag.startSlideShow(check);
        }
    }

    @Override
    public void onGalleryCheckStatusChanged(Boolean check)
    {
        if(check==true)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.imageFragContainerView, galleryFragment).addToBackStack("gallery fragment").commit();
        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.imageFragContainerView,imageFragment).addToBackStack("imagefragment").commit();
        }
    }

}