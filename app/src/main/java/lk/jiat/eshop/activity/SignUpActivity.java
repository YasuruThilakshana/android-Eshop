package lk.jiat.eshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import lk.jiat.eshop.R;
import lk.jiat.eshop.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {


    private ActivitySignUpBinding binding;

    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

         firebaseFirestore = FirebaseFirestore.getInstance();




        binding.signupBtnSignup.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();

        });

        binding.signupBtnSignup.setOnClickListener(View -> {


            String name = binding.signUpName.getText().toString().trim();
            String email = binding.signupEmail.getText().toString().trim();
            String password = binding.signupPassword.getText().toString().trim();
            String confirmPassword = binding.retypePassword.getText().toString().trim();


            if (name.isEmpty()){
                binding.signUpName.setError("Name is required");
                binding.signUpName.requestFocus();
                return;
            }


            if (email.isEmpty()){
                binding.signupEmail.setError("Email is required");
                binding.signupEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.signupEmail.setError("Please provide valid email");
                binding.signupEmail.requestFocus();
                return;
            }
            if (password.isEmpty()){
                binding.signupPassword.setError("Password is required");
                binding.signupPassword.requestFocus();
                return;
            }

            if (password.length() < 6) {
                binding.signupPassword.setError("Min password length should be 6 characters");
                binding.signupPassword.requestFocus();
                return;

            }

           if(!confirmPassword.equals(password)) {
               binding.retypePassword.setError("Password does not match");
               binding.retypePassword.requestFocus();
               return;
           }

firebaseAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()){


        }

        }
    });

        } );

    }
}