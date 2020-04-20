package com.tracker.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.tracker.R;
import com.tracker.adapters.FavoritesAdapter;
import com.tracker.data.FirebaseDb;
import com.tracker.models.serie.SerieResponse;

import java.util.ArrayList;
import java.util.List;

public class FavoritosFragment extends Fragment {

    private Context mContext;
    private DatabaseReference databaseRef;
    private List<SerieResponse.Serie> mFavs = new ArrayList<>();
    private FavoritesAdapter favAdapter;
    private RecyclerView rvFavs;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecycler(view);
        getFollowingSeries();

        ImageButton sortAdded = view.findViewById(R.id.added_button);
        ImageButton sortName = view.findViewById(R.id.name_button);
        ImageButton sortLastWatched = view.findViewById(R.id.watched_button);





    }

    private void getFollowingSeries() {
        FirebaseDb.getInstance().getSeriesFav().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mFavs.clear();
                List<SerieResponse.Serie> favTemp = dataSnapshot.getValue(new GenericTypeIndicator<List<SerieResponse.Serie>>() {
                });
                if (favTemp != null) {
                    mFavs.addAll(favTemp);
                    favAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setRecycler(@NonNull View view) {
        favAdapter = new FavoritesAdapter(getActivity(), mFavs);
        rvFavs = view.findViewById(R.id.grid_favoritas);
        rvFavs.setHasFixedSize(true);
        rvFavs.setItemViewCacheSize(20);
        rvFavs.setSaveEnabled(true);
        rvFavs.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvFavs.setAdapter(favAdapter);
    }
}
