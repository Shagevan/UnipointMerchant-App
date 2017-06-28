package com.example.shagee.unipoint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shagee.unipoint.Model.MerchantUserLoginResponse;
import com.example.shagee.unipoint.RestClient.UnipointMerchantClient;
import com.example.shagee.unipoint.RestClient.UnipointMerchantInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText userNmeET;
    private EditText passwordET;
    private Button loginBtn;
    private UnipointMerchantInterface unipointMerchantService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unipointMerchantService = UnipointMerchantClient.getClient().create(UnipointMerchantInterface.class);

        userNmeET = (EditText) findViewById(R.id.userName);
        passwordET = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNmeET.getText().toString();
                String password = passwordET.getText().toString();
                doLogin(userName,password);

            }
        });

    }

    public void doLogin(String userName, String password){
        Call<MerchantUserLoginResponse> call = unipointMerchantService.login(userName,password);
        call.enqueue(new Callback<MerchantUserLoginResponse>() {
            @Override
            public void onResponse(Call<MerchantUserLoginResponse> call, Response<MerchantUserLoginResponse> response) {
                if(response.body().getStatus().equals("true")){
                    SharedPreferences pref = LoginActivity.this.getSharedPreferences("MerchantUserInfo", 0);
                    SharedPreferences.Editor edt = pref.edit();
                    edt.putString("outletName", response.body().getOutletName());
                    edt.putString("outletRefId", response.body().getOutletRefId());
                    edt.putString("merchantUserName", response.body().getMerchantUserName());
                    edt.putString("merchantUserRefId", response.body().getMerchantUserRefId());
                    edt.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    userNmeET.setText("");
                    passwordET.setText("");
                    Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MerchantUserLoginResponse> call, Throwable t) {

            }
        });
    }
}
