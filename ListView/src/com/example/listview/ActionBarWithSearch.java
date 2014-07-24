package com.example.listview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
 
public class ActionBarWithSearch extends Activity  implements OnQueryTextListener {
    TextView mSearchText;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchText = new TextView(this);
        mSearchText.setPadding(10, 10, 10, 10);
        mSearchText.setText("Action Bar Usage example from CoderzHeaven");
        setContentView(mSearchText);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_view, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }
 
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
 
    @Override
    public boolean    onOptionsItemSelected       (MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }
 
    // The following callbacks are called for the SearchView.OnQueryChangeListener
    public boolean onQueryTextChange(String newText) {
        newText = newText.isEmpty() ? "" : "Query so far: " + newText;
        mSearchText.setText(newText);
        mSearchText.setTextColor(Color.GREEN);
        return true;
    }
 
    public boolean      onQueryTextSubmit      (String query) {
        //Toast.makeText(this, "Searching for: " + query + "...", Toast.LENGTH_SHORT).show();
        mSearchText.setText("Searching for: " + query + "...");
        mSearchText.setTextColor(Color.RED);
        return true;
    }
} 