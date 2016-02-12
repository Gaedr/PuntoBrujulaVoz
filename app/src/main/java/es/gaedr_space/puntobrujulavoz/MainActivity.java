package es.gaedr_space.puntobrujulavoz;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements CompassFragment.OnFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.compass_fragment, new VoiceRecognitionFragment())
                .commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
