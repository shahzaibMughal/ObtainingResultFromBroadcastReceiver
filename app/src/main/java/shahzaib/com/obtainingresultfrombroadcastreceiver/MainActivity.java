package shahzaib.com.obtainingresultfrombroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstNumberET, secondNumberET;
    TextView resultTV;
    ResultReceiver resultReceiver = new ResultReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNumberET = findViewById(R.id.firstNumberET);
        secondNumberET = findViewById(R.id.secondNumberET);
        resultTV = findViewById(R.id.resultTV);

    }

    public void add_numbers_in_BR_and_receive_result(View view) {
        if(firstNumberET.getText().toString().length()>0 && secondNumberET.getText().toString().length()>0)
        {
            int firstNumber = Integer.parseInt(firstNumberET.getText().toString());
            int secondNumber = Integer.parseInt(secondNumberET.getText().toString());
            firstNumberET.setText("");
            secondNumberET.setText("");

            Intent intent = new Intent();
            intent.setAction("ACTION_ADD_NUMBERS");
            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("FirstNumber",firstNumber);
            intent.putExtra("SecondNumber",secondNumber);
            sendOrderedBroadcast(intent,null,resultReceiver,null,RESULT_OK,null,null);
            Toast.makeText(this, "Numbers are successfully transferred  to Broadcast Receiver", Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(this, "first & second number is Missing ", Toast.LENGTH_SHORT).show();
        }
    }



    public class ResultReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = getResultExtras(true);
            int receivedResult = bundle.getInt("Result",-1);
            if(receivedResult == -1) resultTV.setText("NULL");
            else resultTV.setText(String.valueOf(receivedResult));

            Log.i("123456","Result Obtained From Broadcast Receiver: "+receivedResult);

        }
    }
}
