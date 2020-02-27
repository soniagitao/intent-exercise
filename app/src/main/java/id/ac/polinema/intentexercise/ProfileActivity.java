package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

public class ProfileActivity extends AppCompatActivity  {
    private TextView name;
    private TextView email;
    private TextView password;
    private TextView confirm;
    private TextView home;
    private TextView about;
    private Button visit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.text_fullname);
        email = findViewById(R.id.text_email);
        password = findViewById(R.id.text_password);
        confirm = findViewById(R.id.text_confirm_password);
        home = findViewById(R.id.text_homepage);
        about = findViewById(R.id.text_about);
        visit = findViewById(R.id.button_homepage);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String fullnameText = extras.getString(RegisterActivity.NAME_KEY);
            name.setText(fullnameText);
            String emailText = extras.getString(RegisterActivity.EMAIL_KEY);
            email.setText(emailText);
            String passwordText = extras.getString(RegisterActivity.PASSWORD_KEY);
            password.setText(passwordText);
            String confirmPassword = extras.getString(RegisterActivity.CONFIRM_KEY);
            confirm.setText(confirmPassword);
            String homeText = extras.getString(RegisterActivity.HOME_KEY);
            home.setText(homeText);
            String aboutText = extras.getString(RegisterActivity.ABOUT_KEY);
            about.setText(aboutText);
        }
    }


}
