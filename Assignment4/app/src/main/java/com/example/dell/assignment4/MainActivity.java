package com.example.dell.assignment4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String states;
    int position;
    static public int ex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);




        final EditText name = findViewById(R.id.edit_name);
        final EditText email = findViewById(R.id.email);
        final EditText mobileNo = findViewById(R.id.MobNo);
        final EditText city = findViewById(R.id.City);
        final Spinner state = findViewById(R.id.statename);
        Button submit = findViewById(R.id.submit);

        final String ALLstates[] = {"Uttrakhand", "Uttar Pradesh", "Himachal Pradesh", "Madhya Pradesh", "Odisha"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, ALLstates);
        state.setAdapter(arrayAdapter);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                states = ALLstates[i];
                position=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "You haven't selected anything ! ", Toast.LENGTH_LONG).show();
            }
        });







        final String UKEY = "name", MOB = "Mob", EMAIL = "email";
        final SharedPreferences sharedprf = getSharedPreferences("myshare", Context.MODE_PRIVATE);


        if (sharedprf.contains(UKEY) && sharedprf.contains(MOB) && sharedprf.contains(EMAIL))
        {
            Intent i = new Intent(MainActivity.this,Login_ACtivity.class);
            startActivity(i);
        }
        else {

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String uName = name.getText().toString();
                    String mob = mobileNo.getText().toString();
                    String em = email.getText().toString();

                    SharedPreferences.Editor er = sharedprf.edit();

                    er.putString(UKEY, uName);
                    er.putString(MOB, mob);
                    er.putString(EMAIL, em);
                    er.commit();

                    Toast.makeText(MainActivity.this,"Signed UP Succesfully",Toast.LENGTH_LONG);

                    Intent intent = new Intent(MainActivity.this, Information_Activity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("mail", email.getText().toString());
                    intent.putExtra("phone", mobileNo.getText().toString());
                    intent.putExtra("state", states);
                    intent.putExtra("state_position", (position)+"");
                    intent.putExtra("city", city.getText().toString());
                    startActivity(intent);
                }
            });

        }


        name.setText(getIntent().getStringExtra("name2"));
        email.setText(getIntent().getStringExtra("mail2"));
        mobileNo.setText(getIntent().getStringExtra("phone2"));
        city.setText(getIntent().getStringExtra("city2"));
        position=getIntent().getIntExtra("string_position",0);
        state.setSelection(position);




    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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


            AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
            a_builder.setMessage("ADo You want to close this APP")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
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
