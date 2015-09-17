package materialdemo.androidbeasts.com.materialdesigndemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v4.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    CollapsingToolbarLayout collapse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton=(FloatingActionButton) findViewById(R.id.fab);
        collapse=(CollapsingToolbarLayout) findViewById(R.id.collapseToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapse.setTitle("Title");
        collapse.setCollapsedTitleTextColor(Color.parseColor("#FFFFFF"));
        collapse.setExpandedTitleColor(Color.parseColor("#FFFFFF"));
        collapse.setStatusBarScrimColor(Color.parseColor("#FFFFFF"));
        //toolbar.setNavigationIcon(R.drawable.ic_);
        mRecyclerView = (RecyclerView) findViewById(R.id.uid);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        final ArrayList<String> myDataset=new ArrayList<>();
        myDataset.add("first");
        myDataset.add("second");myDataset.add("third");
        myDataset.add("fourth");
        myDataset.add("fifth");
        myDataset.add("sixth");

        final ArrayList<String> mImageArray=new ArrayList<>();
        mImageArray.add("http://www.planwallpaper.com/static/images/1080p-HD-Wallpapers-9.jpg");
        mImageArray.add("http://www.planwallpaper.com/static/images/Cool-Autumn-Nature-Wallpaper-Picture-1860.jpg");
        mImageArray.add("http://www.planwallpaper.com/static/images/Nature-Wallpaper-8.jpg");
        mImageArray.add("http://www.planwallpaper.com/static/images/nature-wallpapers-free-download-1.jpg");
        mImageArray.add("http://www.planwallpaper.com/static/images/nature-wallpapers-1.jpg");
        mImageArray.add("http://www.planwallpaper.com/static/images/best-nature-top-hd-wallpapers-widescreen.jpg");
        mRecyclerView.setLayoutManager(llm);
        mAdapter = new MyAdapter(MainActivity.this,myDataset,mImageArray);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this,new RecyclerItemClickListener.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view,int position){
                        // TODO Handle item click
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra(DetailsActivity.Names,myDataset.get(position));
                        intent.putExtra(DetailsActivity.ImageTs,mImageArray.get(position));
                        //startActivity(intent);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                          // the context of the activity
                                MainActivity.this,
                                // For each shared element, add to this method a new Pair item,
                                // which contains the reference of the view we are transitioning *from*,
                                // and the value of the transitionName attribute
                                new Pair<View, String>(view.findViewById(R.id.imagess),
                                        getString(R.string.transition_name_circle)),
                                new Pair<View, String>(view.findViewById(R.id.person_name),
                                        getString(R.string.transition_name_name))
                        );

                        ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
                    }
                })
        );
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDataset.size()>=3) {
                    myDataset.remove(2);
                    mImageArray.remove(2);
                    mAdapter.notifyItemRemoved(2);
                    mAdapter.notifyItemRangeChanged(2, 1);
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
