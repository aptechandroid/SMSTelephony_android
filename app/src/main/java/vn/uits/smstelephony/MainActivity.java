package vn.uits.smstelephony;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager mTelephone;
    private PhoneStateListener mState;

    private TextView mTvPhone;
    private String mTvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvPhone = findViewById(R.id.mTvPhone);

        mTelephone = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        mState = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        mTvState = "IDLE";
                        break;

                    case TelephonyManager.CALL_STATE_RINGING:
                        mTvState = "RING RING ";
                        break;

                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        mTvState = "OFF ";
                        break;
                }
                super.onCallStateChanged(state, incomingNumber);
            }
        };

        mTelephone.listen(mState, TelephonyManager.CALL_STATE_IDLE);

        mTvPhone.setText(mTvState);
    }

    private void startSMS() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("0935 366 007 ", null, "Content Messager", null, null);
    }
}
