package com.example.mnnitcentral;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class forget_pass extends AppCompatActivity {
    String codesent;
    Button verify_otp;
    EditText enter_otp;
    TextView resend_otp,welcome_message;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        verify_otp=findViewById(R.id.button2);
        enter_otp=findViewById(R.id.editText7);
//        resend_otp=findViewById(R.id.textView4);
        welcome_message=findViewById(R.id.textView5);

        String phoneno = getIntent().getStringExtra("phone");
        welcome_message.setText("OTP Sent to Your Number "+phoneno);

        sendOTPtouser(phoneno);

        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code =enter_otp.getText().toString();
                if (code.isEmpty()){
                    enter_otp.setError("enter the OTP");
                    enter_otp.requestFocus();
                    return;
                }
                verifycode(code);

            }
        });



    }

    private void sendOTPtouser(String phoneno) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneno,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                               // Activity (for callback binding)
                TaskExecutors.MAIN_THREAD,
                callback);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codesent=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String codebyuser = phoneAuthCredential.getSmsCode();
                if (codebyuser!=null){
                    verifycode(codebyuser);
                }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(forget_pass.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };

    private void verifycode(String codebyuser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesent,codebyuser);
        signinthecodebyuser(credential);
    }

    private void signinthecodebyuser(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final String storedpass=getIntent().getStringExtra("passwd");
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(forget_pass.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(forget_pass.this, "Your Password is "+storedpass, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), nextactivity.class);
                            intent.putExtra("name",getIntent().getStringExtra("name"));
                            intent.putExtra("phone",getIntent().getStringExtra("phone"));
                            intent.putExtra("mail",getIntent().getStringExtra("mail"));
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(forget_pass.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
