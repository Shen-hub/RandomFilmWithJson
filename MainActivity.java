package com.example.randomfilmwithjson;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

class Movie {
    String title;
    String genre;
    String year;
    String rated;
    String time;

    Movie (String title, String genre, String year, String rated, String time) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rated = rated;
        this.time = time;
    }
}

class Movies {
    ArrayList<Movie> movies;

    Movies (ArrayList<Movie> movies) {
        this.movies = movies;
    }
}

public class MainActivity extends AppCompatActivity {

    TextView title;
    TextView genre;
    TextView year;
    TextView rated;
    TextView time;

    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream stream = getResources().openRawResource(R.raw.movies);
        Gson gson = new Gson();
        Movies movies_arr = gson.fromJson(new InputStreamReader(stream), Movies.class);

        movies = movies_arr.movies;
        title = findViewById(R.id.title);
        genre = findViewById(R.id.genre);
        year = findViewById(R.id.year);
        rated = findViewById(R.id.rated);
        time = findViewById(R.id.time);

    }

    public void randomFilm(View v) {
        if (movies.size() != 0) {
            int randomId = new Random().nextInt(movies.size());
            genre.setText(movies.get(randomId).genre);
            title.setText(movies.get(randomId).title);
            year.setText(movies.get(randomId).year);
            rated.setText(movies.get(randomId).rated);
            time.setText(movies.get(randomId).time);

            movies.remove(randomId);
        }
        else {
            genre.setText("Фильмы закончились, нажмите кнопку сброса.");
            title.setText(" ");
            year.setText(" ");
            rated.setText(" ");
            time.setText(" ");
        }
    }

    public void reset(View V){
        movies.clear();
        genre.setText(" ");
        InputStream stream = getResources().openRawResource(R.raw.movies);
        Gson gson = new Gson();
        Movies movies_arr = gson.fromJson(new InputStreamReader(stream), Movies.class);
        movies = movies_arr.movies;
    }
}
