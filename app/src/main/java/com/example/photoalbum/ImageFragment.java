package com.example.photoalbum;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ImageFragment extends Fragment
{
    private final Integer[] images={R.drawable.animal13,R.drawable.animal14,R.drawable.animal15,R.drawable.animal16,R.drawable.animal17,R.drawable.animal18};
    private ImageView imageView;
    private Timer timer;
    private Integer slideshowIndex;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageFragment newInstance(String param1, String param2) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = root.findViewById(R.id.imageView);
        //Initialize image view to the first image
        imageView.setImageResource(R.drawable.animal13);

        //Set image to the index specified by option buttons(next/prev)
        Bundle b =this.getArguments();
        int index =0;
        if(b!=null)
        {
            index =b.getInt("Index", 0);
            imageView.setImageResource(images[index]);
        }
        slideshowIndex = 0;
        //
        timer = new Timer();
        return root;
    }

    public void startSlideShow(Boolean isChecked)
    {
        if(isChecked)
        {
            //Schedules the specified task for repeated fixed-delay execution, beginning after the specified delay
            timer.schedule(new TimerTask() {
                @Override
                public void run()
                {
                    int i = slideshowIndex % 6; //loop back to the start
                    imageView.setImageResource(images[i]);
                    slideshowIndex++;
                }
            }, 1000, 1000);
        }
        else
        {
            timer.cancel(); //terminate the timer and terminate the executing thread
            timer = new Timer();
        }
    }
}