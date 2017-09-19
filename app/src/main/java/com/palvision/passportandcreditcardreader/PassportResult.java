package com.palvision.passportandcreditcardreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PassportResult extends AppCompatActivity {

    TextView mIssuingCountryCode, mNationalityCountryCode, mDocumentNumber, mDocumentType, mGivenNames, mSurNames, mDayOfBirth, mExpirationDate, mSex, mMrzString;
    String IssuingCountryCode, NationalityCountryCode, DocumentNumber, DocumentType, GivenNames, SurNames, DayOfBirth, ExpirationDate, Sex, MrzString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport_result);

        initUI();
        getData();
        setData();
    }
    public void initUI() {
        mIssuingCountryCode = (TextView) findViewById(R.id.tv_pr_issuingcountrycode);
        mNationalityCountryCode = (TextView) findViewById(R.id.tv_pr_nationalitycountrycode);
        mDocumentNumber = (TextView) findViewById(R.id.tv_pr_documentnumber);
        mDocumentType = (TextView) findViewById(R.id.tv_pr_document_type);
        mGivenNames = (TextView) findViewById(R.id.tv_pr_given_names);
        mSurNames = (TextView) findViewById(R.id.tv_pr_SurNames);
        mDayOfBirth = (TextView) findViewById(R.id.tv_pr_dayofbirth);
        mExpirationDate = (TextView) findViewById(R.id.tv_pr_expiration_date);
        mSex = (TextView) findViewById(R.id.tv_pr_sex);
        mMrzString = (TextView) findViewById(R.id.tv_pr_mrz_string);
    }

    public void getData() {

        IssuingCountryCode = getIntent().getExtras().getString("IssuingCountryCode");
        NationalityCountryCode = getIntent().getExtras().getString("NationalityCountryCode");
        DocumentNumber = getIntent().getExtras().getString("DocumentNumber");
        DocumentType = getIntent().getExtras().getString("DocumentType");
        GivenNames = getIntent().getExtras().getString("GivenNames");
        SurNames = getIntent().getExtras().getString("SurNames");
        DayOfBirth = getIntent().getExtras().getString("DayOfBirth");
        ExpirationDate = getIntent().getExtras().getString("ExpirationDate");
        Sex = getIntent().getExtras().getString("Sex");
        MrzString = getIntent().getExtras().getString("MrzString");
    }
    public void setData() {
        mIssuingCountryCode.setText("IssuingCountryCode : " + IssuingCountryCode);
        mNationalityCountryCode.setText("NationalityCountryCode : " + NationalityCountryCode);
        mDocumentNumber.setText("DocumentNumber : " + DocumentNumber);
        mDocumentType.setText("DocumentType : " + DocumentType);
        mGivenNames.setText("GivenNames : " + GivenNames);
        mSurNames.setText("SurNames : " + SurNames);
        mDayOfBirth.setText("DayOfBirth" + DayOfBirth);
        mExpirationDate.setText("ExpirationDate : " + ExpirationDate);
        mSex.setText("Sex : " + Sex);
        mMrzString.setText("MRZ : " + MrzString);
    }
}
