package com.example.tam.a112networking_23_24_25;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText mEdtUrl, mEdtLoanAmount, mEdtRate, mEdtMounth;
    private TextView mTxtMonthPayment, mTxtTotalPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtUrl = (EditText)findViewById(R.id.edt_url);
        mEdtLoanAmount = (EditText)findViewById(R.id.edt_loanAmount);
        mEdtRate = (EditText)findViewById(R.id.edt_rate);
        mEdtMounth = (EditText)findViewById(R.id.edt_month);
        mTxtMonthPayment = (TextView)findViewById(R.id.txt_mounthPayment);
        mTxtTotalPayment = (TextView)findViewById(R.id.txt_totalPayment);
        Button btnPayment = (Button)findViewById(R.id.btn_payment);
        btnPayment.setOnClickListener(new ClickPayment());
    }

    private class ClickPayment implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String url = mEdtUrl.getText().toString();
            String loanAmount = mEdtLoanAmount.getText().toString();
            String rate = mEdtRate.getText().toString();
            String month = mEdtMounth.getText().toString();
            new PeymentResult().execute(loanAmount, rate, month);
        }
    }

    class PeymentResult extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... params) {
            LoanInputs inputs = new LoanInputs(params[0], params[1], params[2]);
            JSONObject inputsJson = new JSONObject(inputs.getInputMap());
            String jsonString = null;
            try {
                String url = "http://apps.coreservlets.com/NetworkingSupport/loan-calculator";
                jsonString = HttpUtils.urlContentPost(url, "loanInput", inputsJson.toString() );
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jsonResult = null;
            try {
                jsonResult = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonResult;

        }

        @Override
        protected void onPostExecute(JSONObject response) {
            super.onPostExecute(response);
            if(response != null){
                try {
                    mTxtTotalPayment.setText("Total Payments : "+response.getString("formattedTotalPayments"));
                    mTxtMonthPayment.setText("Month Payents : "+response.getString("formattedMonthlyPayment"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
