package com.vincis.betradict;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vincis.betradict.Class.Wallet;
import com.vincis.betradict.transition_act.transSupport;
import com.vincis.betradict.transition_act.trans_activity;
import com.vincis.betradict.transition_act.trans_leader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class trans_prevMatches extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView one,two,three,four,five;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;
    public Bundle arr;
    String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
    TextView tvU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_prev_matches);


        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        mDrawer = findViewById(R.id.drawerpml);
        mToggle = new ActionBarDrawerToggle(trans_prevMatches.this, mDrawer, R.string.open, R.string.close);
        mDrawer.addDrawerListener(mToggle);
        NavigationView navigationView = findViewById(R.id.nav_viewpml);
        navigationView.setNavigationItemSelectedListener(this);
        arr =getIntent().getExtras();
        String[] data=arr.getStringArray("details");
        View header=navigationView.getHeaderView(0);
        tvU=header.findViewById(R.id.tvUName);
        tvU.setText("Welcome! "+FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        navigationView.setItemIconTintList(null);


        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    one=findViewById(R.id.one);
    two=findViewById(R.id.two);
    three=findViewById(R.id.three);
    // five=findViewById(R.id.five);
    viewPager=findViewById(R.id.container2);
    pagerViewAdapter=new PagerViewAdapter(getSupportFragmentManager(),arr);
        viewPager.setAdapter(pagerViewAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            onChangeTab(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    });



}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart_action);
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("wallet");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Wallet wallet=dataSnapshot.getValue(Wallet.class);
                menuItem.setIcon(Converter.convertLayoutToImage(trans_prevMatches.this,(int)wallet.balance,R.drawable.wallet));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(trans_prevMatches.this,wallet_trans.class));
                return false;
            }
        });

        return true;
    }

    private void onChangeTab(int i) {
        if(i==0)
        {
            one.setImageResource(R.mipmap.quiz);
            two.setImageResource(R.mipmap.ans);
            three.setImageResource(R.mipmap.analytics);

            viewPager.setCurrentItem(0);

        }

        if(i==1)
        {
            one.setImageResource(R.mipmap.question);
            two.setImageResource(R.mipmap.ansc);
            three.setImageResource(R.mipmap.analytics);
            viewPager.setCurrentItem(1);
        }
        if(i==2)
        {
            one.setImageResource(R.mipmap.question);
            two.setImageResource(R.mipmap.ans);
            three.setImageResource(R.mipmap.analyticsc);

            viewPager.setCurrentItem(2);

        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if(id==R.id.home)
        {
            startActivity(new Intent(this,trans_activity.class));
            finish();
        }

        if(id==R.id.logout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, login_act.class));
            finish();
        }
        if(id==R.id.wallet)
        {
            startActivity(new Intent(this,wallet_trans.class));
            finish();
        }
        if(id==R.id.leader_board)
        {
            startActivity(new Intent(this, trans_leader.class));
            finish();
        }
        if(id==R.id.prevml)
        {
            startActivity(new Intent(this, trans_prevmatchList.class));
            finish();
        }
        if(id==R.id.support)
        {
            startActivity(new Intent(this, transSupport.class));
            finish();
        }
      /*  if(id==R.id.suppA)
        {
            startActivity(new Intent(this, trans_adminMList.class));
            finish();
        }

        if(id==R.id.write_quest)
        {
            startActivity(new Intent(this, addQuest.class));
            finish();
        }
        if(id==R.id.addEvent)
        {
            startActivity(new Intent(this, addEvent.class));
            finish();
        }

        if(id==R.id.addDynamic)
        {
            startActivity(new Intent(this, addDynamic.class));
            finish();
        }
*/

        return false;
    }
}
