package binarygeek.sociallogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import binarygeek.sociallogin.FacebookHelperClasses.FacebookHelper;
import binarygeek.sociallogin.FacebookHelperClasses.FacebookListener;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , FacebookListener,View.OnClickListener {

    Button mFacebookButton;
    private FacebookHelper mFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFacebookButton = (Button) findViewById(R.id.facebook_button);
        mFacebookButton.setOnClickListener(this);
        mFacebook = new FacebookHelper(this);


        taskOfDrawer();

    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        mFacebook.onActivityResult(requestCode, resultCode, data);

    }



    @Override
    public void onFbSignInFail(String errorMessage) {
        Log.d("GK","onFbSignInFail");

        Toast.makeText(this,"Facebook login failed ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFbSignInSuccess(String authToken, String userId, String email, String name) {
        Log.d("GK","onFbSignInSuccess");

        Toast.makeText(this,"Facebook login success : Your AuthToken is - "+authToken,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFBSignOut() {
        Toast.makeText(this,"Facebook login out ",Toast.LENGTH_LONG).show();
        Log.d("GK","onFBSignOut");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Toast.makeText(this,"Facebook login onPointerCaptureChanged ",Toast.LENGTH_LONG).show();

        Log.d("GK","onPointerCaptureChanged");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.facebook_button:
                    mFacebook.performSignIn(this);
                    break;

        }
    }







    void taskOfDrawer(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
