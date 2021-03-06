package com.hostelmanager.hostelmaster;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar=null;
    NavigationView navigationView=null;
    private FirebaseAuth firebaseAuth;
    boolean doubleBackToExitPressedOnce = false;
    private String userId;
    private DatabaseReference userinfo;
    private StorageReference mStorageRef;
    private ImageView imageView;
    private Uri tri;
    private ProgressDialog progressDialog;
    PhoneAuthCredential phoneAuthCredential;

   /* @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();


        if(currentUser == null){
            startActivity(new Intent(this,MobileAndOTP.class));
            finish();
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait while your data is loading");




        userinfo = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();


       /* if(currentUser == null){
            startActivity(new Intent(this,MobileAndOTP.class));
        }
        //updateUI(currentUser);
        //if user already logged in set Profile(Activity)
        /*if(currentUser!=null){
            progressDialog.show();
            progressDialog.setCancelable(false);
            userinfo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot ds) {


                    UserInfo userInfo = new UserInfo();
                    userInfo.setFname(ds.child(userId).getValue(UserInfo.class).getFname());
                    userInfo.setMob(ds.child(userId).getValue(UserInfo.class).getMob());

                    NavigationView navigationView = findViewById(R.id.nav_view);
                    TextView txtProfileNam = navigationView.getHeaderView(0).findViewById(R.id.account);
                    txtProfileNam.setText(userInfo.getFname());
                    progressDialog.dismiss();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            FirebaseUser user = firebaseAuth.getCurrentUser();
            userId = user.getUid();

            final NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            imageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
            StorageReference storageReference2= mStorageRef.child("profile").child(user.getUid());
            try{
                storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(getApplicationContext()).load(uri).into(imageView);
                    }
                });
            }
            catch(Exception e){
                progressDialog.dismiss();
                Toast.makeText(this,""+e,Toast.LENGTH_SHORT).show();
            }
            TextView txtProfileNam = navigationView.getHeaderView(0).findViewById(R.id.viewaccount);
            TextView txtProfileName = navigationView.getHeaderView(0).findViewById(R.id.account);
            txtProfileNam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    profiler prof=new profiler();
                    android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.relativefrag , prof).commit();
                }
            });
            txtProfileName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    profiler prof=new profiler();
                    android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.relativefrag , prof).commit();
                }
            });
        } else {
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            TextView txtProfileName = navigationView.getHeaderView(0).findViewById(R.id.viewaccount);
            txtProfileName.setText("");
            TextView txtProfileNam = navigationView.getHeaderView(0).findViewById(R.id.account);
            txtProfileNam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,SignInerActivity.class));
                }
            });
            progressDialog.dismiss();
        }*/


        Explore explore=new Explore();
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.relativefrag,explore).commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView accc=navigationView.getHeaderView(0).findViewById(R.id.viewaccount);
        accc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity.this,MainActivity.class));
                Explore explore=new Explore();
                android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.relativefrag,explore).commit();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Explore explore=new Explore();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativefrag, explore).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return ;
        } else if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,Notifications.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.explore) {
            Explore explore=new Explore();
            android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativefrag,explore).commit();

        } else if (id == R.id.myhostel) {
            //startActivity(new Intent(this,hostel.class));
            MyHostell myHostell=new MyHostell();
            android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativefrag,myHostell).commit();

        } else if (id == R.id.myaccount) {
            profiler prof=new profiler();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativefrag , prof).commit();
        } else if (id == R.id.mywallet) {
            MyWallet myWallet=new MyWallet();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativefrag , myWallet).commit();


        } else if (id == R.id.issues) {
            startActivity(new Intent(this,MyIssues.class));

        }else if (id == R.id.referandearn) {
           // startActivity(new Intent(this,refer_and_earn.class));
            ReferandEarn referandEarn=new ReferandEarn();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relativefrag , referandEarn).commit();
        } else if (id == R.id.feedback) {
            startActivity(new Intent(this,feedback.class));

        } else if (id == R.id.workwithus) {
            startActivity(new Intent(this,workwithus.class));

        }/*else if (id == R.id.aboutus) {

            startActivity(new Intent(this,MyIssues.class));

        }else if (id == R.id.rateus) {
            startActivity(new Intent(this,MyIssues.class));
        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
