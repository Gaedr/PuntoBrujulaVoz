/*
 *  Copyright (C) 2016 - Samuel Peregrina Morillas <gaedr0@gmail.com>, Nieves V. Velásquez Díaz <chibi.pawn@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.gaedr_space.puntobrujulavoz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class VoiceRecognitionFragment extends Fragment {
    private final static int DEFAULT_NUMBER_RESULTS = 10;
    private final static String DEFAULT_LANG_MODEL = RecognizerIntent.LANGUAGE_MODEL_FREE_FORM;
    private final static String TAG = VoiceRecognitionFragment.class.getSimpleName();
    private final static int ASR_CODE = 123;

    private CardinalPoint cardinalPoint;
    private int error = 0;

    public VoiceRecognitionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_voice_recognition, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        //Floating Action Button
        FloatingActionButton btnFab = (FloatingActionButton) view.findViewById(R.id.float_button);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen();
            }
        });
    }

    private void listen() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Specify language model
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, DEFAULT_LANG_MODEL);

        // Specify how many results to receive
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, DEFAULT_NUMBER_RESULTS);

        // Especifica un mensaje
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getText(R.string.compass_speak_message));

        // Start listening
        startActivityForResult(intent, ASR_CODE);
    }

    private void showCompass() {
        getFragmentManager().beginTransaction()
                .replace(this.getId(), CompassFragment.newInstance(cardinalPoint, error))
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean correcto = true;

        if (requestCode == ASR_CODE && resultCode == Activity.RESULT_OK && data != null) {
            ArrayList<String> palabras = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String[] listaPalabras = palabras.get(0).split(" ");

            String direccion = listaPalabras[0].toLowerCase();
            Log.d(TAG, "Direccion: " + direccion);
            switch (direccion) {
                case "norte":
                    cardinalPoint = CardinalPoint.Norte;
                    break;
                case "noreste":
                    cardinalPoint = CardinalPoint.Noreste;
                    break;
                case "noroeste":
                    cardinalPoint = CardinalPoint.Noroeste;
                    break;
                case "sur":
                    cardinalPoint = CardinalPoint.Sur;
                    break;
                case "sudeste":
                    cardinalPoint = CardinalPoint.Sudeste;
                    break;
                case "suroeste":
                    cardinalPoint = CardinalPoint.Suroeste;
                    break;
                case "este":
                    cardinalPoint = CardinalPoint.Este;
                    break;
                case "oeste":
                    cardinalPoint = CardinalPoint.Oeste;
                    break;
                default:
                    correcto = false;
                    Snackbar.make(getView(), R.string.compass_error, Snackbar.LENGTH_LONG).show();
            }

            try {
                if (listaPalabras[1] != null) {
                    Log.d(TAG, "Margen: " + listaPalabras[1]);
                    error = Integer.parseInt(listaPalabras[1]);
                } else {
                    correcto = false;
                    Snackbar.make(getView(), R.string.compass_error, Snackbar.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Log.d(TAG, "Error conversion int");
                correcto = false;
                Snackbar.make(getView(), R.string.compass_error, Snackbar.LENGTH_LONG).show();
            }

            if (correcto) {
                showCompass();
            }
        } else {
            Log.d(TAG, "No se pudo reconocer el texto");
            Snackbar.make(getView(), R.string.compass_error, Snackbar.LENGTH_LONG).show();
        }
    }
}
