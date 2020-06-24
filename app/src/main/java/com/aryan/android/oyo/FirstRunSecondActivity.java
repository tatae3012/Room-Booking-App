package com.aryan.android.oyo;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import java.util.Arrays;
import java.util.List;


public class FirstRunSecondActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = FirstRunSecondActivity.this;

    private ConstraintLayout nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private Button appCompatButtonLogin;

    private Button textViewLinkRegister;
    private AppCompatTextView BusinessLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run_second);

        initViews();
        initListeners();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (ConstraintLayout) findViewById(R.id.parent_first_run_second);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail1);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword1);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.editText);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.editText2);

        appCompatButtonLogin = (Button) findViewById(R.id.button);

        textViewLinkRegister = (Button) findViewById(R.id.button2);
        BusinessLink=(AppCompatTextView)findViewById(R.id.BusinessLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        BusinessLink.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                verifyFromSQLite();
                break;
            case R.id.button2:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), FirstRunThirdActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.BusinessLink:
                //Open Business Page
                Intent intentB=new Intent(getApplicationContext(),FirstRunBusiness.class);
                startActivity(intentB);
                break;

        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {
           DatabaseHelper databaseHelper = new DatabaseHelper(activity);
            String name = databaseHelper.getName(textInputEditTextEmail.getText().toString().trim());
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).commit();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putString("name", name).apply();


            Intent intent = new Intent(activity, FirstActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            emptyInputEditText();
            startActivity(intent);
            finish();



        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
