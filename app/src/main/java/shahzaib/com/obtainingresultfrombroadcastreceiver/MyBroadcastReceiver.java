package shahzaib.com.obtainingresultfrombroadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = getResultExtras(true);

        if(intent == null) return;
        if(intent.getAction().equals("ACTION_ADD_NUMBERS"))
        {
            int firstNumber = intent.getIntExtra("FirstNumber",-1);
            int secondNumber = intent.getIntExtra("SecondNumber",-1);
            int result = firstNumber+secondNumber;

            Toast.makeText(context, "Numbers Successfully added in Broadcast Receiver", Toast.LENGTH_LONG).show();
            bundle.putInt("Result",result);
        }
        else
        {
            Toast.makeText(context, "Another intent invoke the receiver", Toast.LENGTH_SHORT).show();
        }

    }
}
