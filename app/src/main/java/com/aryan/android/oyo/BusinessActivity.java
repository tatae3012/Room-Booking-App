package com.aryan.android.oyo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BusinessActivity extends AppCompatActivity implements View.OnClickListener{
    private Button logout;
    private Button add;
    private Button delete;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business1);
        initViews();
        initListeners();


    }
public void initViews(){

        logout=(Button) findViewById(R.id.logout2);
        add=(Button)findViewById(R.id.add);
        delete=(Button)findViewById(R.id.delete);

}
public void initListeners(){

        logout.setOnClickListener(this);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
}

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout2:
                Intent intent = new Intent(BusinessActivity.this, FirstRunSecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", true).apply();
                finish();
                break;
            case R.id.add:
                //do something
                break;
            case R.id.delete:
                //do something
                break;

        }
    }
}
