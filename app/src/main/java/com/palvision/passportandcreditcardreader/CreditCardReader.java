package com.palvision.passportandcreditcardreader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.card.payment.CardIOActivity;
import io.card.payment.CardType;
import io.card.payment.CreditCard;

public class CreditCardReader extends AppCompatActivity {

    private TextView mResultLabel;
    private ImageView mResultImage;
    private ImageView mResultCardTypeImage;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_SCAN_REQUEST_CODE =  99;

    private static final int REQUEST_SCAN = 100;
    private static final int REQUEST_AUTOTEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_reader);

        mResultLabel = (TextView) findViewById(R.id.result);
        mResultImage = (ImageView) findViewById(R.id.result_image);
        mResultCardTypeImage = (ImageView) findViewById(R.id.result_card_type_image);


        Button scanCreditCard = (Button)findViewById(R.id.scan_credit_card);
        assert scanCreditCard != null;
        scanCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scanIntent = new Intent(CreditCardReader.this, CardIOActivity.class);
                // customize these values to suit your needs.
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true);
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, mCvvToggle.isChecked())
                // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
                startActivityForResult(scanIntent, REQUEST_SCAN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()


                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
                Log.d(TAG, "Card Number " + resultDisplayStr);
                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );
                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                }
                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }
                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }
        // else handle other activity results
        */


        String outStr = new String();
        Bitmap cardTypeImage = null;

        if (requestCode == REQUEST_SCAN) {
            CreditCard result = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
            if (result != null) {
                outStr += "Card number: " + result.cardNumber + "\n";

                CardType cardType = result.getCardType();
                cardTypeImage = cardType.imageBitmap(this);
                outStr += "Card type: "  + " = "
                        + cardType.getDisplayName(null) + "\n";

                //   if (mEnableExpiryToggle.isChecked()) {
                outStr += "Expiry: " + result.expiryMonth + "/" + result.expiryYear + "\n";

                outStr += "CVV :" + result.cvv +  "\n";
                //    }

            /*    if (mCvvToggle.isChecked()) {
                    outStr += "CVV: " + result.cvv + "\n";
                }

                if (mPostalCodeToggle.isChecked()) {
                    outStr += "Postal Code: " + result.postalCode + "\n";
                }

                if (mCardholderNameToggle.isChecked()) {
                    outStr += "Cardholder Name: " + result.cardholderName + "\n";
                }
            }

            if (autotestMode) {
                numAutotestsPassed++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onAutotest(null);
                    }
                }, 500);
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            autotestMode = false;
        }*/

                Bitmap card = CardIOActivity.getCapturedCardImage(data);
                mResultImage.setImageBitmap(card);
                mResultImage.setImageBitmap(card);
                mResultCardTypeImage.setImageBitmap(cardTypeImage);

                Log.i(TAG, "Set result: " + outStr);

                mResultLabel.setText(outStr);
            }
        }
    }
}
