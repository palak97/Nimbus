package com.nith.appteam.nimbus.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nith.appteam.nimbus.Activity.MainActivity;
import com.nith.appteam.nimbus.R;
import com.nith.appteam.nimbus.TeamEventActivity;
import com.nith.appteam.nimbus.Utils.TeamInterface;
import com.nith.appteam.nimbus.Model.TeamItem;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment {


    private static final String TEAM_NAME = "name";
    private static final String TEAM_URL = "url";
    public static final String TEAM_ID = "teamKey";

    public TeamFragment() {
        // Required empty public constructor

    }

    private String teamName;
    private String teamPicUrl;
    private ImageView teamImageView;
    private TextView teamNameTextView;
    private CardView cardview;
    private String teamId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teamName = getArguments().getString(TEAM_NAME);
        teamPicUrl = getArguments().getString(TEAM_URL);
        teamId=getArguments().getString(TEAM_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_team, container, false);
        cardview= (CardView) v.findViewById(R.id.cardview);
        teamImageView = (ImageView) v.findViewById(R.id.image_team);
        teamNameTextView = (TextView) v.findViewById(R.id.text_name_team);
        teamNameTextView.setText(teamName);
        Glide.with(this).load(teamPicUrl).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.team).error(R.drawable.team).into(teamImageView);
         cardview.setMaxCardElevation(cardview.getCardElevation()* TeamInterface.MAX_ELEVATION_FACTOR);
         cardview.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(getActivity(),TeamEventActivity.class);
                 i.putExtra(TEAM_ID,teamId);
                 startActivity(i);
             }
         });
        return v;
    }

    public static TeamFragment newInstance(TeamItem teamItem) {
        TeamFragment teamFragment = new TeamFragment();
        Bundle b = new Bundle();
        b.putString(TEAM_NAME, teamItem.getName());
        b.putString(TEAM_URL, teamItem.getUrl());
        b.putString(TEAM_ID,teamItem.getId());
        teamFragment.setArguments(b);
        return teamFragment;
    }

    public CardView getCardview() {
        return cardview;
    }
}
