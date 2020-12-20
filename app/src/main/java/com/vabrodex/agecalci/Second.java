package com.vabrodex.agecalci;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Second extends AppCompatActivity {
    Button btbirth,bttoday,btcalculate;
    TextView tvresult;
    DatePickerDialog.OnDateSetListener dateSetListener1,dateSetListener2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btbirth=findViewById(R.id.bt_birth);
        btcalculate=findViewById(R.id.bt_calculate);
        bttoday=findViewById(R.id.bt_today);
        tvresult=findViewById(R.id.tv_result);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        bttoday.setText(date);
        btbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Second.this,android.R.style.Theme_Holo_Dialog_MinWidth,dateSetListener1,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date = day+"/"+month+"/"+year;
                btbirth.setText(date);

            }
        };

        bttoday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Second.this,android.R.style.Theme_Holo_Dialog_MinWidth,dateSetListener2,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date = day+"/"+month+"/"+year;
                bttoday.setText(date);

            }
        };

        btcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sDate = btbirth.getText().toString();
                String eDate = bttoday.getText().toString();
                SimpleDateFormat simpleDateFormat1= new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date1 =simpleDateFormat1.parse(sDate);
                    Date date2 =simpleDateFormat1.parse(eDate);
                    long startDate = date1.getTime();
                    long endDate = date2.getTime();

                    if(startDate<=endDate){
                        Period period = new Period(startDate,endDate, PeriodType.yearMonthDay());
                        int years = period.getYears();
                        int months= period.getMonths();
                        int days = period.getDays();

                        tvresult.setText(years+"Years / "+months+"Months / "+days+"Days");
                    }
                    else{
                        Toast.makeText(getApplicationContext() ,"Birth Date can't be greater than Today's date!",Toast.LENGTH_SHORT).show();

                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });



    }
}
