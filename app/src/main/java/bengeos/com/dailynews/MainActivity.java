package bengeos.com.dailynews;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import bengeos.com.dailynews.Adapters.Album;
import bengeos.com.dailynews.Adapters.GridSpacingItemDecoration;
import bengeos.com.dailynews.Adapters.ListItemAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context myContext;
    private static RecyclerView recyclerView;
    private static List<Album> albumList;
    private static ListItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myContext = this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        albumList = new ArrayList<Album>();
        Album album1 = new Album();
        album1.setImageID(R.drawable.daily_enjera_1);
        album1.setTitle("ፀሀይ ግባት እና ወጀብ");
        album1.setContent("“እግዚአብሔር ለክብሩ የተከላቸው የጽድቅ ዛፎች እንዲባሉ ለጽዮን አልቃሾች አደርግላቸው ዘንድ፥ በአመድ ፋንታ አክሊልን፥ በልቅሶም ፋንታ የደስታን ዘይት፥ በኀዘንም መንፈስ ፋንታ የምስጋናን መጐናጸፊያ እሰጣቸው ዘንድ ልኮኛል። ” (ኢሳይያስ 61፡3)");
        albumList.add(album1);

        Album album2 = new Album();
        album2.setImageID(R.drawable.daily_enjera_2);
        album2.setTitle("ክርስቶስ በጣቱ ምን ጻፈ?");
        album2.setContent("ይህን የመጽሀፍ ቅዱስ ታሪክ በማነብበት ጊዜ ሁሉ እጅግ መደነቄ የማይቀር ነው። ምናልባትም በገጠራማዋ ገሊላ ይልቁንም በዚች የወይራ ዛፎች ኮረብታ በምትሆን ደብረ ዘይት መንደር ውስጥ የሆነው ሙግት መጪውን የሰው ልጅ እጣ ፈንታ አስቀድሞ የሚያመለክት በመሆኑ ይሆናል።");
        albumList.add(album2);

        Album album3 = new Album();
        album3.setImageID(R.drawable.daily_enjera_3);
        album3.setTitle("ነገር ግን እግዚአብሔር…");
        album3.setContent("“ነገር ግን እግዚአብሔር” የሚሉት እኚህ 3 ቃላት በወንጌል የተትረፈረፉ ናቸው። እንደኔና እንደእናንተ ላሉት ጠፍተው ለነበሩ፤ እራሳቸውን ከእግዚአብሔር ላይ ከተደቀነ አመፃ ሊያድኑ የማይችሉ ኃጢያተኞች ከእነዚህ የዘለሉ ሌላ ሦስት የተስፋ ቃላት ላይኖሩን ይችላል። ");
        albumList.add(album3);

        Album album4 = new Album();
        album4.setImageID(R.drawable.daily_enjera_4);
        album4.setTitle("ሀዘን ላይ ያሉ ሰዎች በበዓል ጊዜ እንድታውቁላቸው የሚፈልጓቸው ነገሮች");
        album4.setContent("በአላት በደረሱ ቁጥር በሄድንበት ቦታ ሁሉ የምናገኛቸው ሰዎች ደስተኞች መሆን እንዳለብን ይነግሩናል። \n" +
                "\n" +
                "ይሁን እንጂ ወዳጆቻቸውን በቅርቡ ላጡ ሰዎች በዓሉ ሊደሰቱበት የሚገባቸው ነገር መሆኑ ቀርቶ እስኪያልፍ የሚጓጉት ነገር ሊሆንባቸው ይችላል። ለበአሉ ታላቅ ደስታን እና ትርጉምን መስጠት የሚችሉት ባህሎች እና ትዕይንቶች፣ የምንወደው ሰው ይህን ደስታ አብሮን ይካፈል ዘንድ አለመቻሉን በሚያስታውሱ አስጨናቂ ትውስታዎች ይዋጣሉ። ብዙዎች እነዚህ የበአል ወቅቶች እስኪያልፉ ድረስ ሊደበቁባቸው የሚችሉበት ጸጥ ያለ ቦታ ባገኝ ብለው ይመኛሉ።");
        albumList.add(album4);


        Album album5 = new Album();
        album5.setImageID(R.drawable.daily_enjera_5);
        album5.setTitle("ብዙ ክርስቲያኖች ደስተኛ ያልሆኑት ለምንድን ነው?");
        album5.setContent("ለምንድን ነው ብዙ ክርስቲያኖች ደስተኛ ያልሆኑት?\n" +
                "\n" +
                "ደስታ የደህንነት ስሜት ነው። “. . .  የነፍሳችሁን መዳን እየተቀበላችሁ፥ በማይነገርና ክብር በሞላበት ሐሤት ደስ ይላችኋል” (1 ጴጥሮስ 1፡8-9) ። ክርስቲያን ከሆናችሁ መንፈስ ቅዱስ በክርስቶስ እንድትደሰቱ ያደርጋችኋል። ውበቱ እና ታላቅነቱ ነፍሳችሁን ያስደንቃታል። ");
        albumList.add(album5);


        myAdapter = new ListItemAdapter(albumList,myContext);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, getWidthDP());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SET_WALLPAPER) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SET_WALLPAPER}, 12);
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private int getWidthDP (){
        Resources r = getResources();
        int widthDP = Math.round(r.getDisplayMetrics().widthPixels/(r.getDisplayMetrics().xdpi / r.getDisplayMetrics().DENSITY_DEFAULT));
        return widthDP/300;
    }
}
