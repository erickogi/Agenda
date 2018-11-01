package com.dev.agenda.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.agenda.Adapters.MeetingsAdapter;
import com.dev.agenda.Data.MeetingViewModel;
import com.dev.agenda.Models.MeetingAgendaModel;
import com.dev.agenda.R;
import com.dev.lishabora.Utils.DateTimeUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        fragment =new FragmentAddMeeting();
        popOutFragments();
        setUpView();


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
