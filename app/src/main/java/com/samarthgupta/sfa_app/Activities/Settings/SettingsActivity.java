package com.samarthgupta.sfa_app.Activities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.samarthgupta.sfa_app.Activities.Settings.AppCompatPreferenceActivity;
import com.samarthgupta.sfa_app.R;


public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
        private Preference change_password, query ;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_headers);

            change_password = findPreference("password");
            query = findPreference("query");


            change_password.setOnPreferenceClickListener(this);
            query.setOnPreferenceClickListener(this);

        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (preference == change_password) {
                startActivity(new Intent(getActivity().getApplicationContext(), ChangePassword.class));
                return true;
            }else if (preference == query){
                startActivity(new Intent(getActivity().getApplicationContext(), QueryActivity.class));
                return true;
            }
            return false;
        }
    }
}