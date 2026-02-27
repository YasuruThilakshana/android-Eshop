package lk.jiat.eshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import lk.jiat.eshop.R;
import lk.jiat.eshop.databinding.ActivityMainBinding;
import lk.jiat.eshop.databinding.SideNavHeaderBinding;
import lk.jiat.eshop.fragment.CartFragment;
import lk.jiat.eshop.fragment.CategoryFragment;
import lk.jiat.eshop.fragment.HomeFragment;
import lk.jiat.eshop.fragment.LoginFragment;
import lk.jiat.eshop.fragment.MessageFragment;
import lk.jiat.eshop.fragment.OrdersFragment;
import lk.jiat.eshop.fragment.ProfileFragment;
import lk.jiat.eshop.fragment.SettingFragment;
import lk.jiat.eshop.fragment.WishlistFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {

private ActivityMainBinding binding;

private SideNavHeaderBinding sideNavHeaderBinding;
    private DrawerLayout drawerLayout;

     private MaterialToolbar toolbar;

     private NavigationView navigationView;

      private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        View headerView = binding.sideNavigationView.getHeaderView(0);

        sideNavHeaderBinding = SideNavHeaderBinding.bind(headerView);



        drawerLayout = binding.drowerLayout;
        toolbar = binding.toolbar;
        navigationView = binding.sideNavigationView;
        bottomNavigationView = binding.bottomNavigationView;

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {


           @Override
           public void handleOnBackPressed() {
               if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                   drawerLayout.closeDrawer(GravityCompat.START);
               }else{
                   finish();
               }

           }
           });


       navigationView.setNavigationItemSelectedListener(this);
       bottomNavigationView.setOnItemSelectedListener(this);


       if (savedInstanceState == null){

           loadFragment(new HomeFragment());

       }


       }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

       int itemId = menuItem.getItemId();

         Menu navMenu =  navigationView.getMenu();
         Menu bottomNavMenu = bottomNavigationView.getMenu();

         for (int i = 0; i < navMenu.size(); i++){
             navMenu.getItem(i).setChecked(false);
         }

         for (int i = 0; i < bottomNavMenu.size(); i++){
             bottomNavMenu.getItem(i).setChecked(false);
         }

       if (itemId == R.id.side_nav_home || itemId == R.id.bottom_nav_home){

           loadFragment(new HomeFragment());
//           navigationView.setCheckedItem(R.id.side_nav_home);
          navigationView.getMenu().findItem(R.id.side_nav_home).setChecked(true);
           bottomNavigationView.getMenu().findItem(R.id.bottom_nav_home).setChecked(true);

       }else if (itemId == R.id.side_nav_profile || itemId == R.id.bottom_nav_person){

           loadFragment( new ProfileFragment());
//           navigationView.setCheckedItem(R.id.side_nav_profile);
            navigationView.getMenu().findItem(R.id.side_nav_profile).setChecked(true);
            bottomNavigationView.getMenu().findItem(R.id.bottom_nav_person).setChecked(true);


       } else if (itemId == R.id.side_nav_orders){

            loadFragment(new OrdersFragment());
            navigationView.getMenu().findItem(R.id.side_nav_orders).setChecked(true);
       } else if (itemId == R.id.side_nav_wishlist) {

            loadFragment(new WishlistFragment());
            navigationView.getMenu().findItem(R.id.side_nav_wishlist).setChecked(true);

       } else if (itemId == R.id.side_nav_cart || itemId == R.id.bottom_nav_cart){

            loadFragment(new CartFragment());
            navigationView.getMenu().findItem(R.id.side_nav_cart).setChecked(true);
            bottomNavigationView.getMenu().findItem(R.id.bottom_nav_cart).setChecked(true);


        }else if (itemId == R.id.side_nav_message){

            loadFragment(new MessageFragment());
            navigationView.getMenu().findItem(R.id.side_nav_message).setChecked(true);


       }else if (itemId == R.id.side_nav_setting) {

            loadFragment(new SettingFragment());
            navigationView.getMenu().findItem(R.id.side_nav_setting).setChecked(true);


       }else if (itemId == R.id.bottom_nav_category){

           loadFragment(new CategoryFragment());
           bottomNavigationView.getMenu().findItem(R.id.bottom_nav_category).setChecked(true);


       }else if (itemId == R.id.side_nav_login){

//           loadFragment(new LoginFragment());
//           navigationView.getMenu().findItem(R.id.side_nav_login).setChecked(true);

           Intent intent = new Intent(MainActivity.this, SignInActivity.class);
           startActivity(intent);
           finish();




       }else if (itemId == R.id.side_nav_logout){

       }

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

//       drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    private void loadFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }
}