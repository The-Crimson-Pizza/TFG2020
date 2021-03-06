package com.thecrimsonpizza.tvtracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.thecrimsonpizza.tvtracker.R;
import com.thecrimsonpizza.tvtracker.models.serie.Credits;
import com.thecrimsonpizza.tvtracker.util.Constants;
import com.thecrimsonpizza.tvtracker.util.Util;

import java.util.List;

import static com.thecrimsonpizza.tvtracker.util.Constants.BASE_URL_IMAGES_PORTRAIT;

/**
 * Adapter from the RecyclerView that hosts the Actor basic info
 */
public class ActorBasicAdapter extends RecyclerView.Adapter<ActorBasicAdapter.ViewHolder> {

    private List<Credits.Cast> mCastSerie;
    private final Context mContext;

    public ActorBasicAdapter(Context context, List<Credits.Cast> castSerie) {
        if (castSerie != null) {
            this.mCastSerie = castSerie;
        }
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorBasicAdapter.ViewHolder holder, final int position) {
        holder.bindTo(mCastSerie.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        if (mCastSerie != null) {
            return mCastSerie.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView actorPortrait;
        final TextView actorName;
        final TextView actorCharacter;
        int actorId;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            actorPortrait = itemView.findViewById(R.id.profile_image);
            actorName = itemView.findViewById(R.id.actor_name);
            actorCharacter = itemView.findViewById(R.id.character_name);

            itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.ID_ACTOR, actorId);
                if (Util.isNetworkAvailable(itemView.getContext())) {
                    Navigation.findNavController(v).navigate(R.id.action_series_to_actores, bundle);
                } else {
                    Snackbar.make(v, itemView.getContext().getString(R.string.no_conn), BaseTransientBottomBar.LENGTH_LONG)
                            .setAction(R.string.activate_net, v1 -> itemView.getContext().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS))).show();
                }
            });
        }

        static ActorBasicAdapter.ViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cast_vertical, parent, false);
            return new ActorBasicAdapter.ViewHolder(view);
        }

        void bindTo(Credits.Cast cast, Context context) {
            if (cast != null) {
                actorName.setText(cast.name);
                actorCharacter.setText(cast.character);
                actorId = cast.id;
                Util.getImagePortrait(BASE_URL_IMAGES_PORTRAIT + cast.profilePath, actorPortrait, context);
            }
        }
    }
}
