package com.example.photoalbum;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionsFragment extends Fragment {

    private Button btn_prev;
    private Button btn_next;
    private Integer imageIndex;
    private CheckBox chk_slideshow;
    private  CheckBox chk_gallery;
    onButtonPressListener buttonListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OptionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OptionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OptionsFragment newInstance(String param1, String param2) {
        OptionsFragment fragment = new OptionsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_options, container, false);
        btn_prev = root.findViewById(R.id.btn_Previous);
        btn_next = root.findViewById(R.id.btn_Next);
        imageIndex = 0;
        chk_slideshow = root.findViewById(R.id.checkBox_SlideShow);
        chk_gallery = root.findViewById(R.id.checkBox_Gallery);
        SetButtonVisibility();

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageIndex--;
                SetButtonVisibility();
                buttonListener.onButtonPressed(imageIndex);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageIndex++;
                //Log.e("imgind", imageIndex.toString());
                SetButtonVisibility();
                buttonListener.onButtonPressed(imageIndex);
            }
        });

        chk_slideshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && chk_gallery.isChecked())
                {
                    Toast.makeText(getActivity(),"Please close gallery to view slide show",Toast.LENGTH_SHORT).show();
                    chk_slideshow.toggle();
                }
                else
                {
                    if(isChecked)
                        buttonListener.onCheckStatusChanged(true);
                    else
                        buttonListener.onCheckStatusChanged(false);
                }
            }
        });

        chk_gallery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked && chk_slideshow.isChecked())
                {
                    Toast.makeText(getActivity(),"Please close slideshow to view gallery",Toast.LENGTH_SHORT).show();
                    chk_gallery.toggle();
                }

                else
                {
                    if(isChecked)
                        buttonListener.onGalleryCheckStatusChanged(true);
                    else
                        buttonListener.onGalleryCheckStatusChanged(false);
                }

            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            buttonListener = (onButtonPressListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onButtonPressed");
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
        buttonListener=null;
    }

    public void SetButtonVisibility()
    {
        if (imageIndex == 0)
            btn_prev.setEnabled(false);
        else if(imageIndex >= 5)
            btn_next.setEnabled(false);
        else if(imageIndex>0 && imageIndex<5)
        {
            btn_next.setEnabled(true);
            btn_prev.setEnabled(true);
        }
    }
}