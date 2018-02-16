package com.android.firebasesample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FireBaseSignUpPage extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference dbref;
    TextView email;
    TextView displayname;
    DaveCustom daveCustom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_sign_up_page);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        dbref = database.getReference("/davefire/profile");
        if (user == null) {
            startActivity(new Intent(this, FireBaseLogin.class));
            finish();
            return;
        }
        daveCustom = (DaveCustom) findViewById(R.id.circular_view);
        email = (TextView) findViewById(R.id.email);
        displayname = (TextView) findViewById(R.id.display_name);
        email.setText(user.getEmail());
        displayname.setText(user.getDisplayName());
    }

    public void signOut(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(
                                    FireBaseSignUpPage.this,
                                    FireBaseLogin.class));
                            finish();
                        } else {
// Report error to user
                        }
                    }
                });
    }


    public void deleteAccount(View view) {
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(
                                    FireBaseSignUpPage.this,
                                    FireBaseLogin.class));
                            finish();
                        } else {
// Report error to user
                        }
                    }
                });
    }

    public void saveToDatabase(View view) {
        //dbref.setValue(email.getText());
        daveCustom.setCircleLabel(displayname.getText().toString());
        dbref.setValue(displayname.getText());
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("message1/title", "Title 1");
        childUpdates.put("message1/content", "Content string 1");
        childUpdates.put("message2/title", "Title 2");
        childUpdates.put("message2/content", "Content string 2");
        dbref.updateChildren(childUpdates);
        Toast.makeText(this, displayname.getText() + " saved", Toast.LENGTH_SHORT).show();
    }
}
