
package com.example.sample01;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String fragmentName = HogeFragment.class.getCanonicalName();
        Fragment fragment = Fragment.instantiate(this, fragmentName);
        // transaction.addToBackStack(null);
        // これがないと画面開店後の fragment.getActivity() が null になる。
        // なんで?
        transaction.addToBackStack(null);
        transaction.replace(R.id.main_layout, fragment, HogeFragment.TAG);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
