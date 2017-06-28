package com.example.shagee.unipoint;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shagee.unipoint.Model.GetCustomerDetailResponse;
import com.example.shagee.unipoint.RestClient.UnipointMerchantClient;
import com.example.shagee.unipoint.RestClient.UnipointMerchantInterface;
import com.google.android.gms.vision.barcode.Barcode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private CustomViewPager customViewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private ImageView qrCodeReaderView;
    private Toolbar mToolbar;
    private UnipointMerchantInterface unipointMerchantService;
    private static final int REQUEST_CODE = 100;
    private static final int CAMERA_PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unipointMerchantService = UnipointMerchantClient.getClient().create(UnipointMerchantInterface.class);
        setDisplay();
    }

    public void setDisplay(){
        tabLayout = (TabLayout)findViewById(R.id.tabLayoutMain);
        customViewPager = (CustomViewPager) findViewById(R.id.viewPagerMain);
        customViewPager.setSwipeLocked(true);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        qrCodeReaderView = (ImageView) findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_REQUEST);
                }
                else {
                    Intent intent = new Intent(MainActivity.this,QRScanActivity.class);
                    startActivityForResult(intent,REQUEST_CODE);
                }
            }
        });

        mainViewPagerAdapter = new MainViewPagerAdapter(MainActivity.this,getSupportFragmentManager());
        customViewPager.setAdapter(mainViewPagerAdapter);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_points));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_offers));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_coupons));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    customViewPager.setCurrentItem(0);
                }
                if (tab.getPosition() == 1){
                    customViewPager.setCurrentItem(1);
                }
                if (tab.getPosition() == 2){
                    customViewPager.setCurrentItem(2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if(data != null){
                Barcode barcode = data.getParcelableExtra("barcode");
                String cardNumber = barcode.displayValue.toString();
                Call<GetCustomerDetailResponse> call = unipointMerchantService.getCustomerDetails(cardNumber);
                call.enqueue(new Callback<GetCustomerDetailResponse>() {
                    @Override
                    public void onResponse(Call<GetCustomerDetailResponse> call, Response<GetCustomerDetailResponse> response) {

                        // Save unipoint Customer Details
                        SharedPreferences pref = MainActivity.this.getSharedPreferences("CustomerDetails", 0);
                        SharedPreferences.Editor edt = pref.edit();
                        edt.putString("unipointCustomerRefId", response.body().getUnipointCustomerRefId());
                        edt.putString("customerMobileNumber", response.body().getPrimaryMobileNumber());
                        edt.commit();

                        // Popup with customer details
                        Intent intent = new Intent(MainActivity.this, UnipointCustomerAlertDialog.class);
                        intent.putExtra("firsName", response.body().getFirstName());
                        intent.putExtra("lastName", response.body().getLastName());
                        intent.putExtra("mobileNumber", response.body().getPrimaryMobileNumber());
                        intent.putExtra("cardNumber", response.body().getCardNumber());
                        intent.putExtra("cardStatus", response.body().getCardStatus());
                        intent.putExtra("loyaltyLevel", response.body().getSchemeLevel());
                        intent.putExtra("totalPoints", response.body().getTotalPoints());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<GetCustomerDetailResponse> call, Throwable t) {

                    }
                });
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(MainActivity.this,QRScanActivity.class);
                    startActivityForResult(intent,REQUEST_CODE);
                } else {
                    Toast.makeText(getApplicationContext(),"You should enable camera permission to scan QR code",Toast.LENGTH_LONG).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
