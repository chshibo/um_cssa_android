package edu.umich.umcssa.umich_cssa;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import edu.umich.umcssa.umich_cssa.news.NewsFragment;
import edu.umich.umcssa.umich_cssa.recentActivities.RecentActivitiesFragment;
import edu.umich.umcssa.umich_cssa.sales.SalesFragment;
import edu.umich.umcssa.umich_cssa.schedule.ScheduleFragment;
import edu.umich.umcssa.umich_cssa.settings.SettingsFragment;
import edu.umich.umcssa.umich_cssa.tickets.TicketsFragment;

/**
 * Navigation drawer activity
 * Shows fragment on content_main
 * @author Shibo Chen
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SettingsFragment.OnFragmentInteractionListener,
        RecentActivitiesFragment.OnFragmentInteractionListener,NewsFragment.OnFragmentInteractionListener,
        SalesFragment.OnFragmentInteractionListener,TicketsFragment.OnFragmentInteractionListener,
        ScheduleFragment.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //T
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
            SettingsFragment settingsFragment=new SettingsFragment();
            replaceWithFragment(settingsFragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //TODO: MAYBE USEFULL...
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recentActivities) {
            RecentActivitiesFragment recentActivitiesFragment=new RecentActivitiesFragment();
            replaceWithFragment(recentActivitiesFragment);
        } else if (id == R.id.nav_news) {
            NewsFragment newsFragment=new NewsFragment();
            replaceWithFragment(newsFragment);
        } else if (id == R.id.nav_tickets) {
            TicketsFragment ticketsFragment=new TicketsFragment();
            replaceWithFragment(ticketsFragment);
        } else if (id == R.id.nav_sales) {
            SalesFragment salesFragment=new SalesFragment();
            replaceWithFragment(salesFragment);
        } else if (id == R.id.nav_courseSchedule) {
            ScheduleFragment scheduleFragment=new ScheduleFragment();
            replaceWithFragment(scheduleFragment);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceWithFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
//        Add this line if you want to enable back_button_clicked
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
