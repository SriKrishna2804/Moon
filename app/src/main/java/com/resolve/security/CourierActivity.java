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

import com.google.gson.Gson;
import com.resolve.security.datados.Output;
import com.resolve.security.datados.PackageRResponse;
import com.resolve.security.datados.PackageRequest;
import com.resolve.security.utils.DeviceIDs;
import com.resolve.security.utils.Preferences;
import com.resolve.security.web.WebAPI;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CourierActivity extends BaseActivity {

    @BindView(R.id.residentInputLayout)
    TextInputLayout residentInputLayout;
    @BindView(R.id.flatNoInputLayout)
    TextInputLayout flatNoInputLayout;
    @BindView(R.id.mobileInputLayout)
    TextInputLayout mobileInputLayout;
    @BindView(R.id.packageFromInputLayout)
    TextInputLayout packageFromInputLayout;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.ivImage)
    ImageView ivImage;

    private Disposable callCourier;

    @Inject
    WebAPI mWebAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);
        ((App) getApplication()).getNetComponent().inject(this);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSubmit.setOnClickListener(v -> handleSubmission());
        ivImage.setOnClickListener(v -> dispatchTakePictureIntent());
    }

    private void handleSubmission() {
        String residentName = residentInputLayout.getEditText().getText().toString();
        String flat = flatNoInputLayout.getEditText().getText().toString();
        String mobile = mobileInputLayout.getEditText().getText().toString();
        String packageFrom = packageFromInputLayout.getEditText().getText().toString();

        if (TextUtils.isEmpty(currentPhotoPath)) {
            showToast(getString(R.string.visitor_pic_not_taken));
        } else if (TextUtils.isEmpty(residentName)) {
            showToast(getString(R.string.name_empty));
        } else if (TextUtils.isEmpty(flat)) {
            showToast(getString(R.string.flat_empty));
        } else if (TextUtils.isEmpty(mobile)) {
            showToast(getString(R.string.mobile_empty));
        } else if (TextUtils.isEmpty(packageFrom)) {
            showToast(getString(R.string.package_from_empty_hint));
        } else {
            callVisitorService(residentName, mobile, packageFrom, flat);
        }
    }

    private void callVisitorService(String name, String mobileNo, String packageFrom, String packageTo) {

        showProgress("Verifying Package...");

        Output o = new Gson().fromJson(Preferences.getString(Preferences.USER_DATA), Output.class);

        PackageRequest packageRequest = new PackageRequest();
        packageRequest.setLocationId(o.getLocationId());
        packageRequest.setUserId(o.getUserId());
        packageRequest.setToken(DeviceIDs.id());
        packageRequest.setPackageFrom(packageFrom);
        packageRequest.setPackageTo(packageTo);
        packageRequest.setMobile(mobileNo);
        packageRequest.setName(name);


        Observable<PackageRResponse> call = mWebAPI.packageRequest(packageRequest);

        callCourier = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess,
                        this::onError,
                        this::hideProgress);
    }

    private void onError(Throwable throwable) {
        showToast(throwable.getMessage());
    }

    private void onSuccess(PackageRResponse packageRResponse) {
        if (packageRResponse != null && packageRResponse.getStatus()) {
// {"status":true,"message":"Please check your mobile for otp","otp":"8556A710","package_id":7}
        }
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
                        getString(R.string.fileprovide_authority),
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
        String timeStamp = new SimpleDateFormat(getString(R.string.file_jpg_name_format)).format(new Date());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(callCourier != null)
            callCourier.dispose();
    }

}
