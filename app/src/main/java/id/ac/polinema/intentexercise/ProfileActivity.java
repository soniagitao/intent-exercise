package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
    private TextView home;
    private TextView about;
    private ImageView imageProfil;
    String homePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.label_fullname);
        email = findViewById(R.id.label_email);
        home = findViewById(R.id.label_homepage);
        about = findViewById(R.id.label_about);
        imageProfil = findViewById(R.id.image_profile);


        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String fullnameText = extras.getString(RegisterActivity.NAME_KEY);
            name.setText(fullnameText);
            String emailText = extras.getString(RegisterActivity.EMAIL_KEY);
            email.setText(emailText);
            homePage = extras.getString(RegisterActivity.HOME_KEY);
            home.setText(homePage);
            String aboutText = extras.getString(RegisterActivity.ABOUT_KEY);
            about.setText(aboutText);

            Bitmap bitmap = (Bitmap) extras.get("Bitmap");
            imageProfil.setImageBitmap(bitmap);
        }
    }


    public void handleHomePage(View view) {
        Uri webpage = Uri.parse(homePage);
        if (!homePage.startsWith("https://") && !homePage.startsWith("http://")){
            homePage = "http://" + homePage;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
