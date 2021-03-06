package com.teamgeny.moviesreview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);

        // compter le nombre de films
        //Log.d("Count", "" + db.getMoviesCount());

        // ajouter des films
        db.addMovie(new Movie("Matrix", "Super film", 3));
        db.addMovie(new Movie("Le Roi Lion", "Cool", 4));
        db.addMovie(new Movie("Interstellar", "Genial", 5));

        // mettre à jour un film
        //db.updateMovie(new Movie(44, "Le Roi Lion", "Sympa", 5));


        // supprimer un film
        //db.deleteMovie(new Movie(1434));

        // récupérer un film
        //Log.d("film", "" + db.getMovie(2323));


        // récupérer les films
        List<Movie> Movies = db.getAllMovies();
        for (Movie Movie : Movies) {
            String log = "Id: " + Movie.getId() + " ,Title: " + Movie.getTitle() + " ,Commentary: " + Movie.getCommentary()  + " ,Rate: " + Movie.getRate();
            Log.d("Movie: : ", log);
        }
    }
}
