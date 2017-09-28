package com.teamgeny.moviesreview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MoviesInfo";
    private static final String TABLE_MOVIES = "Movies";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_COMMENTARY = "commentary";
    private static final String KEY_RATE = "rate";

    DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_movieS_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_COMMENTARY + " TEXT,"
                + KEY_RATE + " INTEGER" + ")";
        db.execSQL(CREATE_movieS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    void addMovie(Movie Movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, Movie.getTitle());
        values.put(KEY_COMMENTARY, Movie.getCommentary());
        values.put(KEY_RATE, Movie.getRate());
        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }

    int updateMovie(Movie Movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, Movie.getTitle());
        values.put(KEY_COMMENTARY, Movie.getCommentary());
        values.put(KEY_RATE, Movie.getRate());
        return db.update(TABLE_MOVIES, values, KEY_ID + " = ?", new String[]{String.valueOf(Movie.getId())});
    }

    void deleteMovie(Movie Movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, KEY_ID + " = ?",
                new String[] { String.valueOf(Movie.getId()) });
        db.close();
    }

    Movie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOVIES, new String[]{KEY_ID,
                        KEY_TITLE, KEY_COMMENTARY}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Movie movie = new Movie(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getInt(3));
        cursor.close();

        return movie;
    }

    List<Movie> getAllMovies() {
        List<Movie> MovieList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Movie Movie = new Movie();
                Movie.setId(Integer.parseInt(cursor.getString(0)));
                Movie.setTitle(cursor.getString(1));
                Movie.setCommentary(cursor.getString(2));
                Movie.setRate(cursor.getInt(3));
                MovieList.add(Movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return MovieList;
    }

    int getMoviesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}

