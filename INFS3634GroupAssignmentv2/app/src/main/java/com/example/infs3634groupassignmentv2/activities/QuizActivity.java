package com.example.infs3634groupassignmentv2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634groupassignmentv2.MainActivity;
import com.example.infs3634groupassignmentv2.R;
import com.example.infs3634groupassignmentv2.adapters.PokemonAdapter;
import com.example.infs3634groupassignmentv2.api.PokemonSpecies;
import com.example.infs3634groupassignmentv2.model.Question;
import com.example.infs3634groupassignmentv2.model.Quiz;
import com.google.gson.Gson;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    Button startQuizButton1;
    RecyclerView recyclerView;
    int selectedQuiz;
    int selectedGym;
    Quiz newQuiz;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        startQuizButton1 = findViewById(R.id.startQuizButton1);
        recyclerView = findViewById(R.id.rv_quizPokemon);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        selectedQuiz = intent.getIntExtra("selectedQuiz", -1);
        selectedGym = intent.getIntExtra("selectedGym", -1);

        startQuizButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuizInProgressActivity.class);
                if(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().size() == 5){
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().clear();
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("height",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(0)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("weight",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(1)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(2)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(3)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(4)));
                } else{
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("height",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(0)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("weight",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(1)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(2)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(3)));
                    MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getQuestionArrayList().add(new Question("type",  MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(4)));
                }
                intent.putExtra("selectedGym", selectedGym);
                intent.putExtra("selectedQuiz", selectedQuiz );
                startActivity(intent);
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        if (MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(0).checkIfSpeciesExist()) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    PokemonSpecies pokemonSpecies = gson.fromJson(response, PokemonSpecies.class);
                    for (int i = 0; i < 5; i++) {
                        System.out.println(pokemonSpecies.getName());
                        System.out.println(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).getName());
                        if (MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).getName().equals(pokemonSpecies.getName())) {
                            MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).setSpecies(pokemonSpecies);
                        }
                    }
                    PokemonAdapter pokemonAdapter = new PokemonAdapter();
                    pokemonAdapter.setData(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList());
                    recyclerView.setAdapter(pokemonAdapter);
                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            };
            for (int i = 0; i < 5; i++) {
                String url = "https://pokeapi.co/api/v2/pokemon-species/" + MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList().get(i).getId() + "/";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
                requestQueue.add(stringRequest);
            }
        } else {
            PokemonAdapter pokemonAdapter = new PokemonAdapter();
            pokemonAdapter.setData(MainActivity.profile.getGame().getGymArrayList().get(selectedGym).getQuizArrayList().get(selectedQuiz).getPokemonArrayList());
            recyclerView.setAdapter(pokemonAdapter);
        }
    }

}
