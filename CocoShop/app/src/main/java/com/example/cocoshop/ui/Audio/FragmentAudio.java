package com.example.cocoshop.ui.Audio;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cocoshop.Adapter.audioadapter.CardItemAudioApdapter;
import com.example.cocoshop.Adapter.audioadapter.CardItemAudioPopularAdapter;
import com.example.cocoshop.Models.audiomodels.Audio;
import com.example.cocoshop.Models.audiomodels.Category;
import com.example.cocoshop.R;
import com.example.cocoshop.Screen.HomeScreen.HomeScreen;
import com.example.cocoshop.Screen.audioscreen.MainAudioActivity;
import com.example.cocoshop.Screen.audioscreen.PlayAudioActivity;
import com.example.cocoshop.dao.audiodao.BundleData;
import com.example.cocoshop.listener.Listener;

import java.util.ArrayList;

public class FragmentAudio extends Fragment {
    private RecyclerView cardItemAudioRecycler;
    private RecyclerView getCardItemAudioPopularRecycler;
    private CardItemAudioPopularAdapter audioPopularAdapter;
    private CardItemAudioApdapter audioApdapter;
    private TextView txViewAll; // View_all Click show All category audio//
    private ArrayList<Audio> data;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Declare //Initialization variable widget
        txViewAll = (TextView)view.findViewById(R.id.view_all);
        cardItemAudioRecycler = (RecyclerView)view.findViewById(R.id.card_item_audio_recycler);
        getCardItemAudioPopularRecycler = (RecyclerView)view.findViewById(R.id.card_item_audio_popular_recycler);
        audioApdapter = new CardItemAudioApdapter(data());
        data = data();
        audioPopularAdapter = new CardItemAudioPopularAdapter(data);
        cardItemAudioRecycler.setAdapter(audioApdapter);
        getCardItemAudioPopularRecycler.setAdapter(audioPopularAdapter);
        getCardItemAudioPopularRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        cardItemAudioRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        onClickViewAll();
        onClickPlayAudio();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        HomeScreen.isCurrentFragment = "AUDIO";
    }

    private void onClickViewAll(){
        txViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainAudioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onClickPlayAudio(){
        final Intent intent = new Intent(getContext(), PlayAudioActivity.class);
        audioPopularAdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                Bundle bundle = new Bundle();
                Audio audio = data.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                startActivity(intent);
            }
        });
        audioApdapter.setPlayAudioListener(new Listener() {
            @Override
            public void listener(int position) {
                Bundle bundle = new Bundle();
                Audio audio = data.get(position);
                intent.putExtra(PlayAudioActivity.KEYAUDIO, BundleData.sendData(audio));
                startActivity(intent);
            }
        });
    }

    private ArrayList<Audio> data(){
        ArrayList<Audio> data = new ArrayList<>();
        data.add(new Audio("Hello","Url","Hana chister","Url",R.drawable
        .background_card_item, Category.EDUCATION));
        data.add(new Audio("Gặp người nước ngoài lần đầu thì sao","Url","Hana chister","Url",R.drawable
                .background_card_item,Category.EDUCATION));
        data.add(new Audio("Gặp người nước ngoài","Url","Hana chister","Url",R.drawable
                .background_card_item,Category.EDUCATION));
        data.add(new Audio("Gặp người nước ngoài","Url","Hana chister","Url",R.drawable
                .background_card_item,Category.EDUCATION));
        data.add(new Audio("Hello","Url","Hana chister","Url",R.drawable
                .background_card_item,Category.EDUCATION));
        return data;
    }
}
