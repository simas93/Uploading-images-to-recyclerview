package com.example.simas.fourthexercise;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.simas.fourthexercise.Model.Nature;
import com.example.simas.fourthexercise.Model.adapter.NatureAdapter;
import com.example.simas.fourthexercise.Model.helper.Utils;
import com.example.simas.fourthexercise.controller.RestManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestManager restManager;
    private NatureAdapter natureAdapter;
    private DatabaseHelper dataBaseHelper;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        configViews();
        restManager = new RestManager();
        dataBaseHelper = new DatabaseHelper(this);

        if (Utils.isNetworkAvailable(getApplicationContext())){
            getFeed();
        }else {
            getFeedFromDatabase();
        }
    }


    private void getFeedFromDatabase() {
        List<Nature> natureList = dataBaseHelper.getNature();

        for (int i = 0; i < natureList.size(); i++){
            Nature nature = natureList.get(i);
            natureAdapter.addNature(nature);
        }
        natureAdapter.notifyDataSetChanged();
    }

    private void getFeed() {
        Call<List<Nature>> listCall = restManager.getNatureApi().getAllNature();
        listCall.enqueue(new Callback<List<Nature>>() {
            @Override
            public void onResponse(Call<List<Nature>> call, Response<List<Nature>> response) {
                if (response.isSuccessful()){
                    List<Nature> natureList = response.body();

                    for (int i = 0; i < natureList.size(); i++){
                        Nature nature = natureList.get(i);
                        dataBaseHelper.insertData(new Nature(nature.getPhotoUrl(), nature.getPhotoName()));
                        natureAdapter.addNature(nature);
                    }
                    natureAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Nature>> call, Throwable t) {

            }
        });
    }

    private void configViews() {
        fragmentManager = getFragmentManager();
        recyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
        natureAdapter = new NatureAdapter(this);
        recyclerView.setAdapter(natureAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void onItemClicked(FragmentLayout fragmentLayout) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragmentLayout).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }
}
