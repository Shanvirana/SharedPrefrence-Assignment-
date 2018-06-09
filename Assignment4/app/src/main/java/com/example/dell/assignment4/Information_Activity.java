package com.example.dell.assignment4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Dell on 06-06-2018.
 */

public class Information_Activity extends AppCompatActivity
{
    int Postion;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_display);

         final TextView name=findViewById(R.id.text_name);
         final TextView email=findViewById(R.id.Text_mail);
         final TextView mobile=findViewById(R.id.text_mobile);
         TextView state=findViewById(R.id.text_state);
         final TextView city=findViewById(R.id.text_city);
         Button back=findViewById(R.id.button_submit);


        Postion=Integer.parseInt(getIntent().getStringExtra("state_position"));
        Log.d("TEST",Postion+"");
        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("mail"));
        mobile.setText(getIntent().getStringExtra("phone"));
        state.setText(getIntent().getStringExtra("state"));
        city.setText(getIntent().getStringExtra("city"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Information_Activity.this,MainActivity.class);
                intent.putExtra("name2",name.getText().toString());
                intent.putExtra("mail2",email.getText().toString());
                intent.putExtra("phone2",mobile.getText().toString());
                intent.putExtra("city2",city.getText().toString());
                intent.putExtra("string_position",Postion);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Exit:
                newGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newGame() {


        AlertDialog.Builder a_builder = new AlertDialog.Builder(Information_Activity.this);
        a_builder.setMessage("Do You want to close this APP")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.ex = 1;
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("ALERT");
        alert.show();


    }


}
