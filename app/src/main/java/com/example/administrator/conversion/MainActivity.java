package com.example.administrator.conversion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
    private RadioGroup RGType;
    private RadioButton rbLength,rbTime;
    private Spinner spInput,spOutput;
    private ArrayAdapter<String> adapterL,adapterT;
    private String[] unitL,unitT;
    private Button btnCaculate;

    private EditText edInput,edOutput;
    private Double input,output,multiple;
    private String typeInput,typeOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCaculate=(Button)findViewById(R.id.btnCaculate);
        edInput=(EditText)findViewById(R.id.etNumber1);
        edOutput=(EditText)findViewById(R.id.etNumber2);
        //construct the RadioGroup and RadioButton
        RGType=(RadioGroup)findViewById(R.id.rgType);
        rbLength=(RadioButton)findViewById(R.id.rbLength);
        rbTime=(RadioButton)findViewById(R.id.rbTime);
        //construct the Spinner
        spInput=(Spinner)findViewById(R.id.spinnerInput);
        spOutput=(Spinner)findViewById(R.id.spinnerOutput);

        //construct the adapter for length_list
        unitL=getResources().getStringArray(R.array.length_list);
        adapterL=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitL);
        //construct the adapter for time_list
        unitT=getResources().getStringArray(R.array.time_list);
        adapterT=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitT);


        RGType.setOnCheckedChangeListener(rgChanged);
        spInput.setOnItemSelectedListener(spItemSelectInput);
        spOutput.setOnItemSelectedListener(spItemSelectOutput);
        btnCaculate.setOnClickListener(btnCaculateOCL);

        input=0.0;
        output=0.0;
        multiple=1.0;
    }

    private View.OnClickListener btnCaculateOCL=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            getInput();

            if(rbLength.isChecked()){
                caculateL();
            }else if(rbTime.isChecked()){
                caculateT();
            }
            edOutput.setText(Double.toString(output));
        }
    };

    private AdapterView.OnItemSelectedListener spItemSelectInput=new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            typeInput=parent.getSelectedItem().toString();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    private AdapterView.OnItemSelectedListener spItemSelectOutput=new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            typeOutput=parent.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    //the check change for RadioGroup
    private RadioGroup.OnCheckedChangeListener rgChanged=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==R.id.rbLength){     
                spInput.setAdapter(adapterL);
                spOutput.setAdapter(adapterL);
            }else if(checkedId==R.id.rbTime){
                spInput.setAdapter(adapterT);
                spOutput.setAdapter(adapterT);
            }
        }
    };

    private void getInput(){
        boolean bCheckResult=true;
        if("".equals(edInput.getText().toString().trim())){
            input=0.0;
        }else{
            try
            {
                input = Double.parseDouble(edInput.getText().toString());
                if (input instanceof Double == false)
                {
                    bCheckResult = false;
                }
            }
            catch(NumberFormatException e)
            {
                bCheckResult = false;
            }
        }
    }

    private void caculateT(){
        if(typeInput.equals(unitT[0])){
            if(typeOutput.equals(unitT[0])){
                output=input;
            }else if(typeOutput.equals(unitT[1])){
                output=input*24;
            }else if(typeOutput.equals(unitT[2])){
                output=input*24*60;
            }else if(typeOutput.equals(unitT[3])){
                output=input*24*60*60;
            }
        }else if(typeInput.equals(unitT[1])){
            if(typeOutput.equals(unitT[0])){
                output=input/24;
            }else if(typeOutput.equals(unitT[1])){
                output=input;
            }else if(typeOutput.equals(unitT[2])){
                output=input*60;
            }else if(typeOutput.equals(unitT[3])){
                output=input*60*60;
            }
        }else if(typeInput.equals(unitT[2])){
            if(typeOutput.equals(unitT[0])){
                output=input/24/60;
            }else if(typeOutput.equals(unitT[1])){
                output=input/60;
            }else if(typeOutput.equals(unitT[2])){
                output=input;
            }else if(typeOutput.equals(unitT[3])){
                output=input*60;
            }
        }
        else if(typeInput.equals(unitT[3])){
            if(typeOutput.equals(unitT[0])){
                output=input/24/60/60;
            }else if(typeOutput.equals(unitT[1])){
                output=input/60/60;
            }else if(typeOutput.equals(unitT[2])){
                output=input/60;
            }else if(typeOutput.equals(unitT[3])){
                output=input;
            }
        }
    }

    private void caculateL(){
        if(typeInput.equals(unitL[0])){
            if(typeOutput.equals(unitL[0])){
                output=input;
            }else if(typeOutput.equals(unitL[1])){
                output=input*100;
            }else if(typeOutput.equals(unitL[2])){
                output=input*1000;
            }
        }else if(typeInput.equals(unitL[1])){
            if(typeOutput.equals(unitL[0])){
                output=input/100;
            }else if(typeOutput.equals(unitL[1])){
                output=input;
            }else if(typeOutput.equals(unitL[2])){
                output=input*10;
            }
        }else if(typeInput.equals(unitL[2])){
            if(typeOutput.equals(unitL[0])){
                output=input/1000;
            }else if(typeOutput.equals(unitL[1])){
                output=input/10;
            }else if(typeOutput.equals(unitL[2])){
                output=input;
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
