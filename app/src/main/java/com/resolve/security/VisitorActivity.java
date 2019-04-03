package com.resolve.security;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.resolve.security.web.WebAPI;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class VisitorActivity extends BaseActivity {

    @BindView(R.id.nameInputLayout)
    TextInputLayout nameInputLayout;
    @BindView(R.id.flatNoInputLayout)
    TextInputLayout flatNoInputLayout;
    @BindView(R.id.mobileInputLayout)
    TextInputLayout mobileInputLayout;
    @BindView(R.id.authorizationInputLayout)
    TextInputLayout authorizationInputLayout;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.ivImage)
    ImageView ivImage;

    private Disposable callLogin;

    @Inject
    WebAPI mWebAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);
        ((App) getApplication()).getNetComponent().inject(this);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSubmit.setOnClickListener(v -> handleSubmission());
        ivImage.setOnClickListener(v -> dispatchTakePictureIntent());
    }

    private void handleSubmission() {
        String name = nameInputLayout.getEditText().getText().toString();
        String flat = flatNoInputLayout.getEditText().getText().toString();
        String mobile = mobileInputLayout.getEditText().getText().toString();
        String authCode = authorizationInputLayout.getEditText().getText().toString();

        if (TextUtils.isEmpty(currentPhotoPath)) {
            showToast(getString(R.string.visitor_pic_not_taken));
        } else if (TextUtils.isEmpty(name)) {
            showToast(getString(R.string.name_empty));
        } else if (TextUtils.isEmpty(flat)) {
            showToast(getString(R.string.flat_empty));
        } else if (TextUtils.isEmpty(mobile)) {
            showToast(getString(R.string.mobile_empty));
        } else if (TextUtils.isEmpty(authCode)) {
            showToast(getString(R.string.authorization_empty));
        } else {
            callVisitorService();
        }
    }

    private void callVisitorService() {

    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.resolve.security.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        } else {
            showToast(getString(R.string.camera_unavailable_hint));
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            showToast("Camera Pic Snapped.");
            Picasso
                    .get()
                    .load(new File(currentPhotoPath))
                    .rotate(90f)
                    .into(ivImage);

        }
    }

}
