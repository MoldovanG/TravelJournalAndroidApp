package com.example.george.travelaplication;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission_group.CAMERA;


public class NewTravelActivity extends AppCompatActivity {

    private static final String TAG = "NEWTRAVELTAG" ;
    private EditText mTripNameEditText;
    private EditText mTripDestinationEditText;
    Uri picUri;

    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 107;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_travel);
        initView();

        Button addImageButton = findViewById(R.id.button_add_image);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(getPickImageChooserIntent(), 200);
            }
        });
        permissions.add(CAMERA);
        permissionsToRequest = findUnAskedPermissions(permissions);
        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissionsToRequest != null) {
            Log.d(TAG,"Testez permisiunile");
            if (permissionsToRequest.size() > 0)
                requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }

    }

        public Intent getPickImageChooserIntent() {

            // Determine Uri of camera image to save.
            Uri outputFileUri = getCaptureImageOutputUri();

            List<Intent> allIntents = new ArrayList();
            PackageManager packageManager = getPackageManager();

            // collect all camera intents
            Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
            for (ResolveInfo res : listCam) {
                Intent intent = new Intent(captureIntent);
                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(res.activityInfo.packageName);
                if (outputFileUri != null) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                }
                allIntents.add(intent);
            }

            // collect all gallery intents
            Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
            for (ResolveInfo res : listGallery) {
                Intent intent = new Intent(galleryIntent);
                intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                intent.setPackage(res.activityInfo.packageName);
                allIntents.add(intent);
            }

            // the main intent is the last in the list (fucking android) so pickup the useless one
            Intent mainIntent = (Intent) allIntents.get(allIntents.size() - 1);
            for (Intent intent : allIntents) {
                if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                    mainIntent = intent;
                    break;
                }
            }
            allIntents.remove(mainIntent);

            // Create a chooser from the main intent
            Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

            // Add all other intents
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

            return chooserIntent;
        }

        private Uri getCaptureImageOutputUri() {
            Uri outputFileUri = null;
            File getImage = getExternalCacheDir();
            if (getImage != null) {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
            }
            return outputFileUri;
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (resultCode == Activity.RESULT_OK) {


                if (getPickImageResultUri(data) != null) {
                    picUri = getPickImageResultUri(data);
                    Log.d(TAG,picUri.toString());
                }

            }
        }
        public Uri getPickImageResultUri(Intent data) {
            boolean isCamera = true;
            if (data != null) {
                String action = data.getAction();
                isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
            }
            return isCamera ? getCaptureImageOutputUri() : data.getData();
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            // save file url in bundle as it will be null on scren orientation
            // changes
            outState.putParcelable("pic_uri", picUri);
        }

        private ArrayList findUnAskedPermissions(ArrayList wanted) {
            ArrayList result = new ArrayList();

            for (Object perm : wanted) {
                if (!hasPermission((String) perm)) {
                    result.add(perm);
                }
            }

            return result;
        }

        private boolean hasPermission(String permission) {
            if (canMakeSmores()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
                }
            }
            return true;
        }
        private boolean canMakeSmores() {
            return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
        }

        private void initView () {
                    mTripNameEditText = findViewById(R.id.trip_name);
                    mTripDestinationEditText = findViewById(R.id.destination_name);
            }

            public void btnSaveNewTravelOnClick (View view){

                Bundle extras = getIntent().getExtras();

                    String mEmail = extras.getString("Email");
                    Log.d(TAG,mEmail);

                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                        .allowMainThreadQueries()
                        .build();


                String mTravelName = mTripNameEditText.getText().toString();
                String mTravelDestination = mTripDestinationEditText.getText().toString();
                TravelObject mTravel = new TravelObject(picUri.toString(), mTravelName, mTravelDestination, false, mEmail);

                db.userDao().insertAll(mTravel);
                Intent mIntent = new Intent(NewTravelActivity.this, NavigationDrawerActivity.class);
                mIntent.putExtra("Email",mEmail);
                startActivity(mIntent);

            }
}

