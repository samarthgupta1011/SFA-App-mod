package com.samarthgupta.sfa_app.Activities.Settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.samarthgupta.sfa_app.Activities.Settings.AppCompatPreferenceActivity;
import com.samarthgupta.sfa_app.R;

import java.util.Arrays;


public class SettingsActivity extends AppCompatPreferenceActivity {
    private int[] ss = {5, 6, 7, 8, 9, 10, 11, 12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("ValidFragment")
    public class MyPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
        private Preference change_password, query, change_perPage;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_headers);

            change_password = findPreference("password");
            query = findPreference("query");
            change_perPage = findPreference("per_page");


            change_password.setOnPreferenceClickListener(this);
            query.setOnPreferenceClickListener(this);
            change_perPage.setOnPreferenceClickListener(this);
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (preference == change_password) {
                startActivity(new Intent(getActivity().getApplicationContext(), ChangePassword.class));
                return true;
            } else if (preference == query) {
                startActivity(new Intent(getActivity().getApplicationContext(), QueryActivity.class));
                return true;
            } else if (preference == change_perPage) {
                final ArrayAdapter<String> adp = new ArrayAdapter<String>(SettingsActivity.this,
                        android.R.layout.simple_spinner_item, Arrays.toString(ss).split("[\\[\\]]")[1].split(", "));
                final Spinner sp = new Spinner(SettingsActivity.this);
                sp.setLayoutParams(new LinearLayout.LayoutParams(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.MATCH_PARENT));
                sp.setAdapter(adp);
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setMessage(" Change Jobs per page to : ");
                builder.setView(sp);
                builder.create().show();

                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        Log.i("selected", item.toString());
                        SharedPreferences.Editor editor = getSharedPreferences("JOB_PER_PAGE", MODE_PRIVATE).edit();
                        editor.putString("per_page", item.toString());
                        editor.apply();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
            return false;
        }
    }
}