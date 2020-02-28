package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final String TAG = RegisterActivity.class.getCanonicalName();

    public static final String NAME_KEY = "fullname";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY ="password";
    public static final String CONFIRM_KEY = "confirmpassword";
    public static final String HOME_KEY = "homepage";
    public static final String ABOUT_KEY = "about";


    @NotEmpty
    private EditText nameEditText;

    @NotEmpty
    @Email
    private EditText emailEditText;

    @NotEmpty
    @Password
    private EditText passwordEditText;

    @NotEmpty
    @ConfirmPassword
    private EditText confirmPasswordEditText;

    @NotEmpty
    private EditText homePageEditText;

    @NotEmpty
    private EditText aboutEditText;

    private ImageView avatarImage;
    protected Validator validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        validator = new Validator(this);
        validator.setValidationListener(this);

        nameEditText = findViewById(R.id.text_fullname);
        emailEditText = findViewById(R.id.text_email);
        passwordEditText = findViewById(R.id.text_password);
        confirmPasswordEditText = findViewById(R.id.text_confirm_password);
        homePageEditText = findViewById(R.id.text_homepage);
        aboutEditText = findViewById(R.id.text_about);
        avatarImage = findViewById(R.id.image_profile);
    }


    @Override
    public void onValidationSucceeded() {
        String fullname = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmpassword = confirmPasswordEditText.getText().toString();
        String homepage = homePageEditText.getText().toString();
        String about = aboutEditText.getText().toString();

        avatarImage.setDrawingCacheEnabled(true);
        Bitmap b = avatarImage.getDrawingCache();

        Intent intent = new Intent(this, ProfileActivity.class);

        intent.putExtra(NAME_KEY,fullname);
        intent.putExtra(EMAIL_KEY,email);
        intent.putExtra(PASSWORD_KEY,password);
        intent.putExtra(CONFIRM_KEY,confirmpassword);
        intent.putExtra(HOME_KEY, homepage);
        intent.putExtra(ABOUT_KEY,about);
        intent.putExtra("Bitmap", b);

        startActivity(intent);
    }


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

    }

    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }


    public void handleOk(View view) {
        validator.validate();
    }
}
