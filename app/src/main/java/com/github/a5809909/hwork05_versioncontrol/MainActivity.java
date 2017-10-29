package com.github.a5809909.hwork05_versioncontrol;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button checkVersionButton = (Button)findViewById(R.id.check_version_button);
        checkVersionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCodeVersion();
            }
        });
        checkCodeVersion();
    }


    private void checkCodeVersion() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                ControlVersion controlVersion = new ControlVersion();
                final Boolean dialogWithoutNo = controlVersion.isForceUpdate();
                final Boolean versionToUpdate = controlVersion.isCorrectVersion();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (versionToUpdate) {
                            showToast("You have the latest version of the best application");
                        } else {

                            if (dialogWithoutNo) {
                                showAlertDialog("Update is available", "Do You want to update?", dialogWithoutNo);
                            } else {
                                showAlertDialog("Update is available", "You must update your application here: " + BuildConfig.UPDATE_URL, dialogWithoutNo);
                            }
                        }

                    }
                });
            }
        }
        ).start();
    }

    private void showAlertDialog(String title, String message, Boolean dialogWithoutNo) {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("Updating. Please wait)");
            }
        });
        if (!dialogWithoutNo) {
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showToast("Please update application later");
                }
            });
        }
        alertDialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
