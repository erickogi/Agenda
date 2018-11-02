package com.dev.agenda.Views;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.agenda.Adapters.MeetingsAdapter;
import com.dev.agenda.Data.MeetingViewModel;
import com.dev.agenda.GeoUtils.PrefrenceManager;
import com.dev.agenda.Models.MeetingAgendaModel;
import com.dev.agenda.Models.MeetingModel;
import com.dev.agenda.R;
import com.dev.lishabora.Utils.DateTimeUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class FragmentList extends Fragment {
    private AlertDialog alertDialogAndroid;

    private View view;private RecyclerView recyclerView;
    private MeetingsAdapter listAdapter;    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private MeetingViewModel viewModel;
    private FloatingActionButton fab;
    private Fragment fragment;

    private List<MeetingAgendaModel> meetingAgendaModels;

    void setUpView() {
        if (fragment != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment)
                    .addToBackStack(null).commit();
        }

    }

    void popOutFragments() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_meetings,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view=view;
        fab=view.findViewById(R.id.fab);
        viewModel = ViewModelProviders.of(this).get(MeetingViewModel.class);

        initList();
        fab.setOnClickListener(v -> {

            add();
        });

    }

    private void add() {
//        fragment =new FragmentAddMeeting();
//        popOutFragments();
//        setUpView();
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_add_meetimg, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        alertDialogBuilderUserInput.setView(mView);
        alertDialogBuilderUserInput.setCancelable(false);


        TextInputEditText edtTitle=mView.findViewById(R.id.edt_meeting_title);
        TextInputEditText edtSub=mView.findViewById(R.id.edt_meeting_sub);
        TextInputEditText edtLoc=mView.findViewById(R.id.edt_meeting_loc);
        CheckBox chbx=mView.findViewById(R.id.checkbox);




        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.setCancelable(false);

        alertDialogAndroid.show();



        MaterialButton btnPositive, btnNegative, btnNeutral;
        TextView txtTitle;
        LinearLayout lTitle;
        ImageView imgIcon;
        btnPositive = mView.findViewById(R.id.btn_positive);
        btnNegative = mView.findViewById(R.id.btn_negative);
        btnNeutral = mView.findViewById(R.id.btn_neutral);
        txtTitle = mView.findViewById(R.id.txt_title);
        lTitle = mView.findViewById(R.id.linear_title);
        imgIcon = mView.findViewById(R.id.img_icon);


        btnNeutral.setVisibility(View.GONE);
        btnNeutral.setText("Delete");

        btnNeutral.setBackgroundColor(this.getResources().getColor(R.color.colorOrange));
        lTitle.setVisibility(View.GONE);
        txtTitle.setVisibility(View.VISIBLE);
        imgIcon.setVisibility(View.VISIBLE);
        txtTitle.setText("New Meeting");

//

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtTitle.getText())){
                    edtTitle.setError("Required");
                    edtTitle.requestFocus();
                }else if(TextUtils.isEmpty(edtLoc.getText())){
                    edtLoc.setError("Required");
                    edtLoc.requestFocus();
                }

                else {
                    String title=edtTitle.getText().toString();
                    String loc=edtLoc.getText().toString();
                    String sub="";
                    if(!TextUtils.isEmpty(edtSub.getText())){
                       sub=edtSub.getText().toString();
                    }
                    String time="";
                    //if(chbx.isChecked()){
                        time=DateTimeUtils.Companion.getNow();
                    //}

                    MeetingModel m=new MeetingModel();
                    m.setTitle(title);
                    m.setSubTitle(sub);
                    m.setStartTimeStamp(time);
                    m.setLocation(loc);
                    m.setLat(new PrefrenceManager(getContext()).getLastLat());
                    m.setLon(new PrefrenceManager(getContext()).getLastLon());

                    viewModel.insert(m);
                    fragment =new FragmentAddMeeting();
     popOutFragments();
        setUpView();
                }
            }
        });

        btnNegative.setOnClickListener(view ->

        {

            alertDialogAndroid.dismiss();
        });


    }

    private void initList() {

        recyclerView = view.findViewById(R.id.recyclerView);
        listAdapter = new MeetingsAdapter(Objects.requireNonNull(getContext()), meetingAgendaModels);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        listAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listAdapter);
        viewModel.fetchAll().observe(this, meetingAgendaModels -> listAdapter.refresh(meetingAgendaModels));

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
