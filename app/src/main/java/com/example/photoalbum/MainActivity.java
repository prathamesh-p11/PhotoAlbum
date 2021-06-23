package com.example.photoalbum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;



//Prathamesh Patil & Devarsh Patel


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

        //Bundle object (An object similar to map having string keys and Parcels(Containers for data) as values)
        //Used to send data to new fragment
        Bundle bundle  = new Bundle();
        bundle.putInt("Index", 0);

        //The data sent through setArgument methods is retained even if activity is recreated and fragment is rebuilt
        imageFragment.setArguments(bundle);

        //FragmentManager class is used to create transactions for adding, removing or replacing fragments
        //getSupportFragmentManager returns the fragment manager for the associated activity
        FragmentTransaction fragTransaction =getSupportFragmentManager().beginTransaction();
        //To add a new fragment: It takes ID of the container for the fragment and class object of fragment to add
        fragTransaction.add(R.id.imageFragContainerView, imageFragment);
        //Commit tells fragment manager that all operations have been added to transaction and it is ready to be executed
        fragTransaction.commit();
    }

    //Method to switch images
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

    //method to start slide show
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

    //method to change view to gallery
    @Override
    public void onGalleryCheckStatusChanged(Boolean check)
    {
        if(check)
        {
            //To replace an existing fragment in a container with an instance of a new fragment
            //Replce can be considered as remove(A) + add(B) to the same container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.imageFragContainerView, galleryFragment).addToBackStack("gallery fragment").commit();
        }
        else
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.imageFragContainerView,imageFragment).addToBackStack("imagefragment").commit();
        }
    }

}