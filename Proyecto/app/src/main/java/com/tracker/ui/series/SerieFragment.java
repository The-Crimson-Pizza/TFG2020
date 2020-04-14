package com.tracker.ui.series;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tracker.R;
import com.tracker.adapters.FillSerie;
import com.tracker.adapters.TabLayoutAdapter;
import com.tracker.data.RepositoryAPI;
import com.tracker.data.RxBus;
import com.tracker.data.SeriesViewModel;
import com.tracker.models.serie.SerieResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import static com.tracker.util.Constants.ID_SERIE;

public class SerieFragment extends Fragment {

    private int idSerie;
    private SerieResponse.Serie mSerie;
    private SeriesViewModel model;
    private Context mContext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_serie, container, false);
        mContext = getActivity();
//        getActivity().getApplicationContext()
        if (getArguments() != null) {
            idSerie = getArguments().getInt(ID_SERIE);
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new ViewModelProvider(getActivity()).get(SeriesViewModel.class);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());

//        ProgressBar bar = view.findViewById(R.id.progreso);
//        bar.setVisibility(View.VISIBLE);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Snackbar.make(v, "Added to favourites", Snackbar.LENGTH_LONG)
                    .setAction("Undo", null).show();
        });

        ViewPager2 viewPager = view.findViewById(R.id.view_pager);
//        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new TabLayoutAdapter(this, false));

        String[] tabs = {getString(R.string.sinopsis), getString(R.string.reparto), getString(R.string.temporadas)};
        TabLayout tabLayout = view.findViewById(R.id.tabs);

        new TabLayoutMediator(tabLayout, viewPager, false,
                (tab, position) -> tab.setText(tabs[position])
        ).attach();

        getSerie(view);
    }

    private void getSerie(View view) {
        RepositoryAPI.getInstance().getSerie(idSerie)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(serie -> {
                    mSerie = serie;
                    new FillSerie(view, mSerie, mContext).fillCollapseBar();
                    RxBus.getInstance().publish(mSerie);
                    model.setSerie(mSerie);
                });
    }
}