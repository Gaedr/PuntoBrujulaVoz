package es.gaedr_space.puntobrujulavoz;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassFragment extends Fragment implements SensorEventListener {
    private OnFragmentInteractionListener mListener;

    public static final String CARDINAL_POINT = "CARDINAL_POINT";
    public static final String MARGIN = "MARGIN";

    private ImageView compassImage;
    private TextView tvDirection;
    private CardinalPoint cardinalPoint;
    private int errorMargin;
    private float currentDegree, degree;
    private boolean directionFound = false;

    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;

    // Guarda el valor del azimut
    float azimut;
    // Guarda los valores que cambián con las variaciones del sensor TYPE_ACCELEROMETER
    float[] mGravity;
    // Guarda los valores que cambián con las variaciones del sensor TYPE_MAGNETIC_FIELD
    float[] mGeomagnetic;

    public CompassFragment() {
    }

    /**
     * Método factoria para devolver una nueva instancia de la Brújula
     *
     * @param cardinalPoint Dirección a la que apuntar.
     * @param margin        Margen de error.
     * @return Una nueva instancia del fragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static CompassFragment newInstance(CardinalPoint cardinalPoint, int margin) {
        CompassFragment fragment = new CompassFragment();
        Bundle args = new Bundle();
        args.putSerializable(CARDINAL_POINT, cardinalPoint);
        args.putInt(MARGIN, margin);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cardinalPoint = (CardinalPoint) getArguments().getSerializable(CARDINAL_POINT);
            errorMargin = getArguments().getInt(MARGIN);
        }

        this.mSensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mGeomagnetic = null;
        mGravity = null;

        compassImage = (ImageView) getActivity().findViewById(R.id.compass_image);
        tvDirection = (TextView) getActivity().findViewById(R.id.direction_text);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compass, container, false);
    }


    public boolean correctDirection(CardinalPoint cp, float angle) {
        float inf = angle * (1.0f - this.errorMargin);
        float sup = angle * (1.0f + this.errorMargin);
        return cp.getAngle() >= inf && cp.getAngle() <= sup;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // Se comprueba que tipo de sensor está activo en cada momento
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                mGravity = event.values;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mGeomagnetic = event.values;
                break;
        }

        if ((mGravity != null) && (mGeomagnetic != null)) {
            float RotationMatrix[] = new float[16];
            boolean success = SensorManager.getRotationMatrix(RotationMatrix, null, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(RotationMatrix, orientation);
                azimut = orientation[0] * (180 / (float) Math.PI);
            }
        }
        degree = azimut;
        tvDirection.setText("Angle: " + Float.toString(degree) + " degrees");
        // se crea la animacion de la rottacion (se revierte el giro en grados, negativo)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        // el tiempo durante el cual la animación se llevará a cabo
        ra.setDuration(1000);
        // establecer la animación después del final de la estado de reserva
        ra.setFillAfter(true);
        // Inicio de la animacion
        compassImage.startAnimation(ra);
        currentDegree = -degree;

        if (!directionFound) {
            if (correctDirection(cardinalPoint, currentDegree)) {

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Se registra un listener para los sensores del accelerometer y el magnetometer
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Se detiene el listener para no malgastar la bateria
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
