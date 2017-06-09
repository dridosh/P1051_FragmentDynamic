package ru.rsfera.p1051_fragmentdynamic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    Fragment1 frag1;
    Fragment2 frag2;
    FragmentTransaction fragmentTransaction;
    CheckBox chbStack;

    FragmentManager fragmentManager;
    ButtonClickListener buttonClickListener;

    Button btnAdd;
    Button btnReplace;
    Button btnRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        buttonClickListener = new ButtonClickListener();

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
        }

        frag1 = new Fragment1();
        frag2 = new Fragment2();

        //   frag1.setArguments(getIntent().getExtras());

        chbStack = (CheckBox) findViewById(R.id.chbStack);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(buttonClickListener);

        btnReplace = (Button) findViewById(R.id.btnReplace);
        btnReplace.setOnClickListener(buttonClickListener);

        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(buttonClickListener);


    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            fragmentTransaction = fragmentManager.beginTransaction();
            switch (v.getId()) {
                case R.id.btnAdd:
                    Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                    if (!(fragment instanceof Fragment1)) {
                        fragmentTransaction.add(R.id.fragment_container, frag1);
                    }

                    break;
                case R.id.btnRemove:
                    fragmentTransaction.remove(frag1);
                    fragmentTransaction.remove(frag2);
                    break;
                case R.id.btnReplace:
                    fragmentTransaction.replace(R.id.fragment_container, frag2);
                default:
                    break;
            }
            if (chbStack.isChecked()) fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


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
