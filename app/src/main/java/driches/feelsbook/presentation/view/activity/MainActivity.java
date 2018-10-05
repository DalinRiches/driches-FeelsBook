package driches.feelsbook.presentation.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import driches.feelsbook.R;
import driches.feelsbook.data.repository.EmotionDataRepository;
import driches.feelsbook.presentation.view.component.EmotionBar;
import driches.feelsbook.presentation.view.component.EmotionIcon;
import driches.feelsbook.presentation.view.fragment.EmotionLogFragment;
import driches.feelsbook.presentation.view.fragment.EmotionStatFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private EmotionBar addEmotionBar;

    private EmotionIcon angerAdd;
    private EmotionIcon fearAdd;
    private EmotionIcon joyAdd;
    private EmotionIcon loveAdd;
    private EmotionIcon sadAdd;
    private EmotionIcon supriseAdd;

    private EmotionLogFragment emotionLogFragment;
    private EmotionStatFragment emotionStatFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmotionDataRepository.initRepository(new File(this.getFilesDir(), getResources().getString(R.string.file_name)));

        init();
    }

    private void init() {
        // Setup Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        this.emotionLogFragment = new EmotionLogFragment();
        this.emotionStatFragment = new EmotionStatFragment();

        // Setup Emotion Bar
        this.addEmotionBar = (EmotionBar) findViewById(R.id.emotion_bar);
        this.addEmotionBar.setCallback(this.emotionLogFragment);

        // Setup ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Setup Tabs
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(this.emotionLogFragment, "LOG");
        adapter.addFragment(this.emotionStatFragment, "STATS");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}