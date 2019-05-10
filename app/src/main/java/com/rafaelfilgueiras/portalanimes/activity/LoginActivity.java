package com.rafaelfilgueiras.portalanimes.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

// Pertencentes aos importes do google

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;



import com.rafaelfilgueiras.portalanimes.R;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "Simples Longin Google: ";
    private FirebaseAuth mAuth;

    public GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Apenas retirar a barra pois para o modelo não fica legal
        getSupportActionBar().hide();

        // ficar atento pois em outros modelos usando API menor tivemos problemas serios por usar o getInstance IMPORTANTE!

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Incluir chamada para o botão e inicio de login
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                Snackbar.make(v, "Aguarde um momento por favor.", Snackbar.LENGTH_SHORT).show();    // Aqui com a snackbar vai ficar muito foda tem que completar
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user); não será usado para essa autenticação.
                            //Toast.makeText(LoginActivity.this, "Autenticação com Sucesso é NOIX!", Toast.LENGTH_LONG).show();
                            Intent intentPrincipal = new Intent(LoginActivity.this, PrincipalActivity.class);
                            startActivity(intentPrincipal);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Erro ao autenticar!", Toast.LENGTH_LONG).show();
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);não será usado para essa autenticação.

                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        try {
            //dialog.dismiss();
        } catch (Exception e) {

        }

        if (user != null) {
            String str_emailgoogle = user.getEmail();
            Log.e("Email", str_emailgoogle);
            //tv_email.setText(str_emailgoogle);
            //tv_name.setText(user.getDisplayName());
            //boolean_google=true;
            //tv_google.setText("Sign out from Google");

            //Glide.with(MainActivity.this).load( user.getPhotoUrl()).into(iv_image);


            Log.e("Profile", user.getPhotoUrl() + "");

        } else {

        }
    }

}



