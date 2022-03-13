package com.example.se2einzelbeispiel;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Huse Pasic
 * 11805084
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final TextView matrikelnummerInput = findViewById(R.id.matrikelnummerInput);

        MyThread myThread = new MyThread();
        myThread = new MyThread();
        new Thread(myThread).start();

        Button sendButton = findViewById(R.id.sendButton);
        String matrikelnummer = matrikelnummerInput.toString();

        MyThread finalMyThread = myThread;
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matrikelnummer = matrikelnummerInput.toString();
                finalMyThread.sendMsgParam(matrikelnummer);
                String antwort = finalMyThread.getAntwort();
                serverDisplayMessage(antwort);
                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence matrikelnummer = matrikelnummerInput.getText();
                        calculate(matrikelnummer);
                    }
                });
            }
        });
    }

    public synchronized void resultDisplayMessage(String message) {
        final TextView serverResponseTextView = findViewById(R.id.matrikelnummerInput);
        serverResponseTextView.setText(message);
    }
    public synchronized void serverDisplayMessage(String message) {
        final TextView serverResponseTextView = findViewById(R.id.serverResponse);
        serverResponseTextView.setText(message);
    }


    private CharSequence calculate(CharSequence matrikelnummer){
        // 11805084 % 7 = 4
        char[] matrikelnummerAsChararray = matrikelnummer.toString().toCharArray();
        int[] matNrAsIntArr = charArrayToIntArray(matrikelnummerAsChararray);
        int quersumme = quersumme(matNrAsIntArr);
        String binärNum = numToBin(quersumme);
        resultDisplayMessage(binärNum);
        return matrikelnummer;
    }

    private int[] charArrayToIntArray (char[] cahrArray){
        char[] charArr = cahrArray;
        int[] intArray = new int[cahrArray.length];

        for (int i = 0; i < cahrArray.length; i++) {
            intArray[i] = Character.getNumericValue(charArr[i]) ;
        }
        return intArray;
    }

    private int quersumme (int[] intArray){
        int summe = 0;
        for (int i: intArray) {
            if (i != 0){
                summe += i;
            }
        }
        System.out.println("Matrikelnummer ");
        System.out.println("Quersumme: " + summe);
        return summe;
    }

    private String numToBin (int dezimal){
        String binaryString = Integer.toBinaryString(dezimal);
        System.out.println(binaryString);
        return binaryString;
    }
}