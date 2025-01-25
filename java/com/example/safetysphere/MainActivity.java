package com.example.safetysphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=findViewById(R.id.list);
        String[] main={
                "Police","Fire","Hospital"
        };
        String[] des={
                "Tirupathur","Tirupathur","Tirupathur"
        };
        int[] images={
                R.drawable.download,
                R.drawable.fire,
                R.drawable.hos
        };
        MyListAdapter adapter=new MyListAdapter(this,main,des,images);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),main[i],Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),List_data.class);
                intent.putExtra("img",images[i]);
                intent.putExtra("text1",main[i]);
                intent.putExtra("text2",des[i]);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu resource file
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle menu item clicks
        int itemId = item.getItemId();
        String name= (String) item.getTitle();
        if (itemId == R.id.menu_item_profile) {
            Intent intent=new Intent(MainActivity.this,Profile.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.menu_item_police) {
            showToast("call police clicked");
            return true;
        } else if (itemId == R.id.menu_item_patrol) {
            showToast("call patrol clicked");
            return true;
        } else if(itemId == R.id.menu_item_about){
            Intent intent=new Intent(MainActivity.this,About.class);
            startActivity(intent);
            return true;
        }
        else if (itemId == R.id.menu_item_settings) {
            showToast("Settings clicked");
            return true;
        }
        else if(name.equals("SOS")){
            Intent intent=new Intent(getApplicationContext(),Sos.class);
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showToast(String message) {
        // Display a toast message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}