//          MADE BY:_            _   _____                        _
//          \ \    / (_)        (_) |  __ \                      (_)
//           \ \  / / _ _ __ ___ _  | |  | | __ _ ___  __ _ _ __  _
//            \ \/ / | | '__/ _ \ | | |  | |/ _` / __|/ _` | '_ \| |
//             \  /  | | | |  __/ | | |__| | (_| \__ \ (_| | | | | |
//              \/   |_|_|  \___| | |_____/ \__,_|___/\__,_|_| |_|_|
//                             _/ |
//                            |__/
//

//CONVERT TO EVERYTHING

package com.virej.converttoeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    public static double PI = 3.14159;


    //-----------------------------global Variables-----------------------------------
    EditText editTextAmount;
    EditText editTextUnit;
    TextView answerTextView;
    Double answer1;
    Double answer2;
    Double answer3;
    Double answer4;
    Double answer5;
    Double answer6;
    Double answer7;
    String finalAnswer1;
    String finalAnswer2;
    String finalAnswer3;
    String finalAnswer4;
    String finalAnswer5;
    String finalAnswer6;
    String finalAnswer7;
    Button convertButton;
    Button clear;
    Button availableUnitsButton;
    Button aboutButton;
    //\\-----------------------------global Variables-----------------------------------



    //------------------------------AUTOCOMPLETE ARRAY-------------------------------------
    String[] availableUnits = { "ml", /*"l",*/ "cc", "gallons","centilitre/cl",
            /*"millilitres",*/ "litres/l", /*"cubic centimetres",*/
            "mm", "cm", /*"m",*/ "km", "dm",
            /*"millimetres", "centimetres",*/ "metres/m"/*,"kilometres", "decimetres",*/,
            "inches","feet", "miles", "yards",
            "radians", "degrees",
            "kg", "grams", "milligrams","pounds", "ounces/oz","tonnes",
            "sec", "min", "hr", "days", "months",
            "celsius", "fahrenheit", "kelvin", "GB", "MB", "KB", "TB" };
    //\\----------------------------AUTOCOMPLETE ARRAY-------------------------------------



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------AUTOCOMPLETE-------------------------------------
        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, availableUnits);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.editTextUnit);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);
        //\\----------------------------AUTOCOMPLETE-------------------------------------


        //-----------------------------Initializers-----------------------------------
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextUnit = (EditText) findViewById(R.id.editTextUnit);
        answerTextView = findViewById(R.id.textViewAnswers);
        convertButton = findViewById(R.id.convert_button);
        clear = findViewById(R.id.clearButton);
        availableUnitsButton = findViewById(R.id.available_units_button);
        aboutButton = findViewById(R.id.about_button);
        //\\-----------------------------Initializers-----------------------------------


        //------------------------------AboutCreatorIntent --------------------------------------------------------

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutCreatorIntent = new Intent(MainActivity.this, CreatorActivity.class);
                startActivity(aboutCreatorIntent);
            }
        });

        //\\----------------------------AboutCreatorIntent ---------------------------------------------------------


        //----------------------------Clear OnClickListener------------------------------
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextAmount.setText("");
                editTextUnit.setText("");
                answerTextView.setText("");
            }
        });
        //\\----------------------------Clear OnClickListener------------------------------



        //------------------------------AvailableUnitsIntent ------------------------------

        availableUnitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent availableUnitsIntent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(availableUnitsIntent);
            }
        });

        //\\----------------------------AvailableUnitsIntent ------------------------------






        //----------------------Convert OnClickListener-----------------------------------
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //get text from EditText amount
                String amount = editTextAmount.getText().toString();
                //get text from EditText unit
                String unit = editTextUnit.getText().toString().toLowerCase();
                //converting string amount into float for math
                double amountFloat = Float.parseFloat(amount);


                //validation
                if (amount.equals("")){
                    Toast.makeText(MainActivity.this, "Both amount and unit are required", Toast.LENGTH_SHORT).show();
                }
                else if(unit.equals("")){
                    Toast.makeText(MainActivity.this, "Both amount and unit are required", Toast.LENGTH_SHORT).show();
                }
                else if (amount.equals("") && unit.equals("")){
                    Toast.makeText(MainActivity.this, "Both amount and unit are required", Toast.LENGTH_SHORT).show();

                }
                //\\----------------------Convert OnClickListener-----------------------------------








//--------------------------------------------VOLUME MEASURES--------------------------------------------------------------------------
                //---------------------ml/cc to litre and centilitre-----------------------------------
                if (
                        unit.equals("ml") ||
                                unit.startsWith("millilit") ||
                                unit.equals("cc") ||
                                unit.startsWith("cubic centimet")){

                    //ml/cc to litre
                    answer1 = round(amountFloat / 1000, 5);
                    //ml/cc to centilitre
                    answer2 = round(amountFloat / 10, 5);

                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    answerTextView.setText((finalAnswer1 + "  litres \n" + finalAnswer2 + "  centiliters" ));

                }
                //\\---------------------ml/cc to litre and centilitre----------------------------------



                //-----------------------------cl to ml/cc and l----------------------------------------
                else if (
                        unit.equals("cl") ||
                                unit.startsWith("centilit") ||
                                unit.equals("centilitres") ||
                                unit.startsWith("centelit")){

                    //cl to ml
                    answer1 = round(amountFloat * 10, 5);
                    //cl to litre
                    answer2 = round(amountFloat / 100, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    answerTextView.setText((finalAnswer1 + "  millilitres \n" + finalAnswer2 + "  litres" ));

                }
                //\\---------------------------cl to ml/cc and l----------------------------------------




                //---------------------litre to ml/cc and centilitre and gallons----------------------------------
                else if (
                        unit.equals("l") ||
                                unit.startsWith("litr") ||
                                unit.startsWith("liter") ||
                                unit.equals("litre")) {

                    //litre to ml
                    answer1 = round(amountFloat * 1000, 5);
                    //litre to centilitre
                    answer2 = round(amountFloat * 100, 5);
                    //litres to gallons
                    answer3 = round(amountFloat / 3.785, 5);

                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    answerTextView.setText((finalAnswer1 + "  millilitres / cubic centimetres\n" +
                            finalAnswer2 + "  centilitres\n" +
                            finalAnswer3 + "  gallons\n"));
                }
                //\\---------------------litre to ml/cc and centilitre----------------------------------

                //gallons to litres


                else if (
                        unit.equals("gallon") ||
                                unit.startsWith("gallon") ||
                                unit.equals("gallons")) {

                    //gallons to l
                    answer1 = round(amountFloat * 3.785, 5);


                    finalAnswer1 = answer1.toString();

                    answerTextView.setText((finalAnswer1 + "  litres\n"));
                }


                //\\gallons to litres

//\\--------------------------------------------VOLUME MEASURES--------------------------------------------------------------------------




//--------------------------------------------DISTANCE MEASURES--------------------------------------------------------------------------
                //millimetre to cm, dm, m, inches, feet
                else if (
                        unit.equals("mm") ||
                                unit.startsWith("millimet") ||
                                unit.startsWith("milimet")) {

                    //mm to cm
                    answer1 = round(amountFloat / 10, 5);
                    //mm to dm
                    answer2 = round(amountFloat / 100, 5);
                    //mm to m
                    answer3 = round(amountFloat / 1000, 5);
                    //mm to inch
                    answer4 = round(amountFloat / 25.4, 5);
                    //mm to feet
                    answer5 = round(amountFloat / 300, 5);

                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();
                    answerTextView.setText((finalAnswer1 + "  centimetres\n" +
                            finalAnswer2 + "  decimetres\n" + finalAnswer3 + "  metres\n" +
                            finalAnswer4 + "  inches\n" + finalAnswer5 + "  feet"));
                }
                //\\millimetre to cm, dm, m, inches, feet

                //cm to mm,dm, m, inches, feet
                else if (
                        unit.equals("cm") ||
                                unit.startsWith("centimet") ||
                                unit.equals("centimetre")) {

                    //cm to mm
                    answer1 = round(amountFloat * 10, 5);
                    //cm to dm
                    answer2 = round(amountFloat / 10, 5);
                    //cm to m
                    answer3 = round(amountFloat / 100, 5);
                    //cm to inches
                    answer4 = round(amountFloat / 2.54, 5);
                    //cm to feet
                    answer5 = round(amountFloat / 30.48, 5);

                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();
                    answerTextView.setText((finalAnswer1 + "  millimetres\n" +
                            finalAnswer3 + "  decimetres\n" + finalAnswer2 + "  metres\n" +
                            finalAnswer4 + "  inches\n" + finalAnswer5 + "  feet"));
                }
                //\\cm to mm,dm, m, inches, feet

                //dm to mm, cm, m, inches, feet, yards

                else if (
                        unit.equals("dm") ||
                                unit.startsWith("decimete") ||
                                unit.startsWith("decimetr") ||
                                unit.equals("decimetre")) {

                    //dm to mm
                    answer1 = round(amountFloat * 100, 5);
                    //dm to cm
                    answer2 = round(amountFloat * 10, 5);
                    //dm to m
                    answer3 = round(amountFloat / 10, 5);
                    //dm to inches
                    answer4 = round(amountFloat * 3.937, 5);
                    //dm to feet
                    answer5 = round(amountFloat / 3.048, 5);
                    //dm to yards
                    answer6 = round(amountFloat / 9.144, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();
                    finalAnswer6 = answer6.toString();
                    answerTextView.setText((finalAnswer1 + "  millimetres\n" +
                            finalAnswer2 + "  centimetres\n" +
                            finalAnswer3 + "  metres\n" +
                            finalAnswer4 + "  inches\n" +
                            finalAnswer5 + "  feet\n" +
                            finalAnswer6 + "  yards\n"));
                }

                //\\dm to mm, cm, m, inches, feet, yards

                //m to dm, cm, km, inches, feet, yards, miles

                else if (
                        unit.equals("m") ||
                                unit.startsWith("metre") ||
                                unit.startsWith("meter") ||
                                unit.equals("metre")) {

                    //m to dm
                    answer1 = round(amountFloat * 10, 5);
                    //m to cm
                    answer2 = round(amountFloat * 100, 5);
                    //m to km
                    answer3 = round(amountFloat / 1000, 5);
                    //m to inches
                    answer4 = round(amountFloat * 39.37, 5);
                    //m to feet
                    answer5 = round(amountFloat * 3.281, 5);
                    //m to yards
                    answer6 = round(amountFloat * 1.094, 5);
                    //m to miles
                    answer7 = round(amountFloat / 1609, 5);

                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();
                    finalAnswer6 = answer6.toString();
                    finalAnswer7 = answer7.toString();

                    answerTextView.setText((finalAnswer2 + "  centimetres\n" +
                            finalAnswer1 + "  decimetres\n" +
                            finalAnswer3 + "  kilometres\n" +
                            finalAnswer4 + "  inches\n" +
                            finalAnswer5 + "  feet\n" +
                            finalAnswer6 + "  yards\n" +
                            finalAnswer7 + "  miles\n"));
                }

                //\\m to dm, cm, km, inches, feet, yards, miles



                //km to m, cm and dm, yards, miles

                else if (
                        unit.equals("km") ||
                                unit.startsWith("kilometre") ||
                                unit.equals("kilomet")) {

                    //km to m
                    answer1 = round(amountFloat * 1000, 5);
                    //km to dm
                    answer2 = round(amountFloat * 10000, 5);
                    //km to cm
                    answer3 = round(amountFloat * 100000, 5);
                    //km to yards
                    answer4 = round(amountFloat * 1093.61, 5);
                    //km to miles
                    answer5 = round(amountFloat / 1.609, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();

                    answerTextView.setText((finalAnswer1 + "  metres\n" +
                            finalAnswer2 + "  decimetres \n" +
                            finalAnswer3 + "  centimetres\n" +
                            finalAnswer4 + "  yards\n" +
                            finalAnswer5 + "  miles\n"));
                }

                //\\km to m, cm and dm, yards, miles





                //inches to mm, cm, dm, m, yards, feet


                else if (
                        unit.equals("in") ||
                                unit.startsWith("inch") ||
                                unit.equals("inches")) {

                    //inches to mm
                    answer1 = round(amountFloat * 25.4, 5);
                    //inches to cm
                    answer2 = round(amountFloat * 2.54, 5);
                    //inches to dm
                    answer3 = round(amountFloat / 3.937, 5);
                    //inches to m
                    answer4 = round(amountFloat / 39.37, 5);
                    //inches to yards
                    answer5 = round(amountFloat / 36, 5);
                    //inches to feet
                    answer6 = round(amountFloat / 12, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();
                    finalAnswer6 = answer6.toString();

                    answerTextView.setText((finalAnswer1 + "  millimetres\n" +
                            finalAnswer2 + "  centimetres \n" +
                            finalAnswer3 + "  decimetres\n" +
                            finalAnswer4 + "  metres\n" +
                            finalAnswer5 + "  yards\n" +
                            finalAnswer6 + "  feet\n"));
                }


                //\\inches to mm, cm, dm, m, yards, feet




                //feet to mm, cm, dm, m, yards, inches


                else if (
                        unit.equals("ft") ||
                                unit.startsWith("feet") ||
                                unit.equals("feet") ||
                                unit.equals("foot")) {

                    //feet to mm
                    answer1 = round(amountFloat * 304.8, 5);
                    //feet to cm
                    answer2 = round(amountFloat * 30.48, 5);
                    //feet to dm
                    answer3 = round(amountFloat * 3.048, 5);
                    //feet to m
                    answer4 = round(amountFloat / 39.37, 5);
                    //feet to yards
                    answer5 = round(amountFloat / 3, 5);
                    //feet in inches
                    answer6 = round(amountFloat * 12, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();
                    finalAnswer6 = answer6.toString();

                    answerTextView.setText((finalAnswer1 + "  millimetres\n" +
                            finalAnswer2 + "  centimetres \n" +
                            finalAnswer3 + "  decimetres\n" +
                            finalAnswer4 + "  metres\n" +
                            finalAnswer5 + "  yards\n" +
                            finalAnswer6 + "  inches\n"));
                }


                //\\feet to mm, cm, dm, m, yards, inches




                //yards to cm, dm, m, km, inches, feet

                else if (
                        unit.equals("yards") ||
                                unit.startsWith("yard") ||
                                unit.equals("yrd") ||
                                unit.equals("yard")) {

                    //yards to cm
                    answer1 = round(amountFloat * 91.44, 5);
                    //yards to dm
                    answer2 = round(amountFloat * 9.144, 5);
                    //yards to m
                    answer3 = round(amountFloat / 1.094, 5);
                    //yards to km
                    answer4 = round(amountFloat / 1094, 5);
                    //yards to inches
                    answer5 = round(amountFloat * 36, 5);
                    //yards to feet
                    answer6 = round(amountFloat * 3, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();
                    finalAnswer6 = answer6.toString();

                    answerTextView.setText((finalAnswer1 + "  centimetres\n" +
                            finalAnswer2 + "  decimetres \n" +
                            finalAnswer3 + "  metres\n" +
                            finalAnswer4 + "  kilometres\n" +
                            finalAnswer5 + "  inches\n" +
                            finalAnswer6 + "  feet\n"));
                }

                //\\yards to cm, dm, m, km, inches, feet



                //miles to m, km

                else if (
                        unit.equals("miles") ||
                                unit.startsWith("mile") ||
                                unit.equals("mile")) {

                    //miles to m
                    answer1 = round(amountFloat * 1609.34, 5);
                    //miles to km
                    answer2 = round(amountFloat * 1.6093, 5);



                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  metres\n" +
                            finalAnswer2 + "  kilometres \n"));
                }

                //\\miles to m, km




//\\------------------------------------------DISTANCE MEASURES--------------------------------------------------------------------------



//--------------------------------------------MASS MEASURES--------------------------------------------------------------------------


                //mg to g

                else if (
                        unit.equals("milligrams") ||
                                unit.startsWith("milligram") ||
                                unit.equals("mg") ||
                                unit.equals("milligram")) {


                    answer1 = round(amountFloat / 1000, 5);

                    finalAnswer1 = answer1.toString();

                    answerTextView.setText((finalAnswer1 + "  grams\n"));
                }

                //\\mg to g



                //g to mg, kg, pounds, ounces

                else if (
                        unit.equals("g") ||
                                unit.startsWith("gram") ||
                                unit.equals("grams")) {

                    //g to mg
                    answer1 = round(amountFloat * 1000, 5);
                    //g to kg
                    answer2 = round(amountFloat / 1000, 5);
                    //g to pounds
                    answer3 = round(amountFloat / 454, 5);
                    //g to ounces
                    answer4 = round(amountFloat / 28.35, 5);

                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();

                    answerTextView.setText((finalAnswer1 + "  milligrams\n" +
                            finalAnswer2 + "  kilograms \n" +
                            finalAnswer3 + "  pounds\n" +
                            finalAnswer4 + "  ounces\n"));
                }

                //\\g to mg, kg, pounds, ounces


                // kg to g, tonnes, pounds, ounces

                else if (
                        unit.equals("kg") ||
                                unit.startsWith("kilogram") ||
                                unit.equals("kilograms")) {

                    //kg to g
                    answer1 = round(amountFloat * 1000, 5);
                    //kg to tonne
                    answer2 = round(amountFloat / 1000, 5);
                    //kg to pounds
                    answer3 = round(amountFloat * 2.205, 5);
                    //kg to ounces
                    answer4 = round(amountFloat * 35.274, 5);

                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();

                    answerTextView.setText((finalAnswer1 + "  grams\n" +
                            finalAnswer2 + "  tonnes \n" +
                            finalAnswer3 + "  pounds\n" +
                            finalAnswer4 + "  ounces\n"));
                }

                //\\ kg to g, tonnes, pounds, ounces


                // tonnes to kg, pounds, ounces

                else if (
                        unit.equals("tonne") ||
                                unit.startsWith("ton") ||
                                unit.equals("tons")) {

                    //tonnes to kg
                    answer1 = round(amountFloat * 1000, 5);
                    //tonnes to pounds
                    answer2 = round(amountFloat * 2204.62, 5);
                    //tonnes to ounces
                    answer3 = round(amountFloat * 35274, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();

                    answerTextView.setText((finalAnswer1 + "  kilograms\n" +
                            finalAnswer2 + "  pounds \n" +
                            finalAnswer3 + "  ounces\n"));
                }

                //\\ tonnes to kg, pounds, ounces




                //pounds to g, kg, ounces

                else if (
                        unit.equals("pounds") ||
                                unit.startsWith("pound") ||
                                unit.equals("pound")) {

                    //pounds to g
                    answer1 = round(amountFloat * 453.59, 5);
                    //pounds to kg
                    answer2 = round(amountFloat / 2.205, 5);
                    //tonnes to ounces
                    answer3 = round(amountFloat * 16, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();

                    answerTextView.setText((finalAnswer1 + "  grams\n" +
                            finalAnswer2 + "  kilograms \n" +
                            finalAnswer3 + "  ounces\n"));
                }

                //\\pounds to g, kg, ounces


                //ounces to pounds, g, kg

                else if (
                        unit.equals("ounces") ||
                                unit.startsWith("ounce") ||
                                unit.equals("oz")) {

                    //ounces to g
                    answer1 = round(amountFloat * 28.35, 5);
                    //ounces to kg
                    answer2 = round(amountFloat / 35.247, 5);
                    //tonnes to ounces
                    answer3 = round(amountFloat / 16, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();

                    answerTextView.setText((finalAnswer1 + "  grams\n" +
                            finalAnswer2 + "  kilograms \n" +
                            finalAnswer3 + "  ounces\n"));
                }

                //\\ounces to pounds, g, kg




//\\------------------------------------------MASS MEASURES--------------------------------------------------------------------------



//------------------------------------------------------TIME--------------------------------------------------------------------------
                //seconds to minutes and hours
                else if (
                        unit.equals("seconds") ||
                                unit.startsWith("second") ||
                                unit.startsWith("sec") ||
                                unit.equals("sec")) {

                    //seconds to minutes
                    answer1 = round(amountFloat / 60, 5);
                    //seconds to hours
                    answer2 = round(amountFloat / 3600, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  minutes\n" +
                            finalAnswer2 + "  hours \n"));
                }
                //\\seconds to minutes and hours




                //minutes to seconds and hours
                else if (
                        unit.equals("minutes") ||
                                unit.startsWith("minut") ||
                                unit.startsWith("min") ||
                                unit.equals("min")) {

                    //minutes to seconds
                    answer1 = round(amountFloat * 60, 5);
                    //minutes to hours
                    answer2 = round(amountFloat / 60, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  seconds\n" +
                            finalAnswer2 + "  hours \n"));
                }
                //\\minutes to seconds and hours


                //hours to minutes, seconds and days
                else if (
                        unit.equals("hours") ||
                                unit.startsWith("hour") ||
                                unit.startsWith("hr") ||
                                unit.equals("hr")) {

                    //hours to minutes
                    answer1 = round(amountFloat * 60, 5);
                    //hours to seconds
                    answer2 = round(amountFloat * 3600, 5);
                    //hours to days
                    answer3 = round(amountFloat / 24, 3);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();

                    answerTextView.setText((finalAnswer2 + "  seconds \n" +
                            finalAnswer1 + "  minutes\n" + finalAnswer3 + "  days\n"));
                }
                //\\hours to minutes and seconds


                //days to hours, minutes and seconds, weeks, months, years

                else if (
                        unit.equals("days") ||
                                unit.startsWith("day") ||
                                unit.equals("day")) {

                    //day to hours
                    answer1 = round(amountFloat * 24, 3);
                    //day to minutes
                    answer2 = round(amountFloat * 1440, 3);
                    //day to seconds
                    answer3 = round(amountFloat * 86400, 3);
                    //day to weeks
                    answer4 = round(amountFloat / 7, 3);
                    //day to months
                    answer5 = round(amountFloat / 30.42, 3);
                    //day ot years
                    answer6 = round(amountFloat / 365,2);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();
                    finalAnswer3 = answer3.toString();
                    finalAnswer4 = answer4.toString();
                    finalAnswer5 = answer5.toString();
                    finalAnswer6 = answer6.toString();

                    answerTextView.setText((finalAnswer1 + "  hours \n" +
                            finalAnswer2 + "  minutes\n" +
                            finalAnswer3 + "  seconds\n" +
                            finalAnswer4 + "  weeks\n" +
                            finalAnswer5 + "  months\n" +
                            finalAnswer6 + "  years\n"));
                }

                //\\days to hours, minutes and seconds, weeks, months, years

                //months to days, years
                else if (
                        unit.equals("months") ||
                                unit.startsWith("month") ||
                                unit.equals("month")) {

                    //months to days
                    answer1 = round(amountFloat * 30.417, 2);
                    //months to years
                    answer2 = round(amountFloat / 12, 3);

                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  days \n" +
                            finalAnswer2 + "  years\n"));
                }
                //\\months to days, years



//\\----------------------------------------------------TIME--------------------------------------------------------------------------



//----------------------------------------------------TEMPERATURE--------------------------------------------------------------------------
                //celsius to fahrenheit and kelvin
                else if (
                        unit.equals("celsius") ||
                                unit.equals("celcius") ||
                                unit.startsWith("celsius") ||
                                unit.startsWith("celcius")) {

                    //celsius to fahrenheit
                    answer1 = round((amountFloat * (1.8)) + 32, 5);
                    //celsius to kelvin
                    answer2 = round(amountFloat + (273.15), 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  fahrenheit\n" +
                            finalAnswer2 + "  kelvin \n"));
                }
                //\\celsius to fahrenheit and kelvin




                //fahrenheit to celsius and kelvin
                else if (
                        unit.equals("fahrenheit") ||
                                unit.equals("farenheit") ||
                                unit.startsWith("faren") ||
                                unit.startsWith("fahren")) {

                    //fahrenheit to celsius
                    answer1 = round((amountFloat - 32) * 5 / 9, 5);
                    //fahrenheit to kelvin
                    answer2 = round((amountFloat - 32) * 5 / 9 + 273.15, 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  celsius\n" +
                            finalAnswer2 + "  kelvin \n"));
                }
                //\\fahrenheit to celsius and kelvin



                //kelvin to celsius and fahrenheit
                else if (
                        unit.equals("kelvin") ||
                                unit.equals("k") ||
                                unit.startsWith("kelv")){

                    //kelvin to celsius
                    answer1 = round((amountFloat - 273.15), 5);
                    //kelvin to fahrenheit
                    answer2 = round(((amountFloat - 273.15) * 9/5 + 32), 5);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  celsius\n" +
                            finalAnswer2 + "  fahrenheit \n"));
                }
                //\\kelvin to celsius and fahrenheit





//\\--------------------------------------------------TEMPERATURE--------------------------------------------------------------------------



//----------------------------------------------------ANGLES--------------------------------------------------------------------------


                //radians to degrees

                else if (
                        unit.equals("radians") ||
                                unit.equals("rad") ||
                                unit.startsWith("rad")){

                    //rad to deg
                    answer1 = round((amountFloat * 180/PI), 5);

                    finalAnswer1 = answer1.toString();

                    answerTextView.setText((finalAnswer1 + "  Deg\n"));
                }

                //\\radians to degrees



                //degrees to radians

                else if (
                        unit.equals("degrees") ||
                                unit.equals("deg") ||
                                unit.startsWith("deg")){

                    //rad to deg
                    answer1 = round((amountFloat * PI/180), 5);

                    finalAnswer1 = answer1.toString();

                    answerTextView.setText((finalAnswer1 + "  Rad\n"));
                }

                //\\degrees to radians



//\\--------------------------------------------------ANGLES--------------------------------------------------------------------------





//----------------------------------------------------STORAGE CAPACITY--------------------------------------------------------------------------


                //kb to mb

                else if (
                        unit.equals("kb") ||
                                unit.equals("kilobyte") ||
                                unit.startsWith("kiloby")){

                    //kb to mb
                    answer1 = round(amountFloat / 1000, 3);


                    finalAnswer1 = answer1.toString();

                    answerTextView.setText((finalAnswer1 + "  MB\n"));
                }
                //\\kb to mb



                //mb to kb, gb

                else if (
                        unit.equals("mb") ||
                                unit.equals("megabyte") ||
                                unit.startsWith("megaby")){

                    //mb to kb
                    answer1 = round(amountFloat * 1000, 3);
                    //mb to gb
                    answer2 = round(amountFloat / 1000, 3);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  MB\n" +
                                            finalAnswer2 + "  GB\n"));
                }
                //\\mb to kb, gb



                //gb to mb, tb

                else if (
                        unit.equals("gb") ||
                                unit.equals("gigabyte") ||
                                unit.startsWith("gigaby")){

                    //gb to mb
                    answer1 = round(amountFloat * 1000, 3);
                    //gb to tb
                    answer2 = round(amountFloat / 1000, 3);


                    finalAnswer1 = answer1.toString();
                    finalAnswer2 = answer2.toString();

                    answerTextView.setText((finalAnswer1 + "  MB\n" +
                            finalAnswer2 + "  TB\n"));
                }
                //\\gb to mb, tb



                //tb to gb

                else if (
                        unit.equals("tb") ||
                                unit.equals("terabyte") ||
                                unit.startsWith("teraby")){

                    //kb to mb
                    answer1 = round(amountFloat * 1000, 3);


                    finalAnswer1 = answer1.toString();

                    answerTextView.setText((finalAnswer1 + "  GB\n"));
                }
                //\\tb to gb


//\\--------------------------------------------------STORAGE CAPACITY--------------------------------------------------------------------------







                //When unit is not recognised
                else {

                    answerTextView.setText(R.string.invalid_unit);

                }
                //\\When unit is not recognised


            } //Convert OnClick
        }); //ConvertButton OnCLickListener




    } //OnCreate


    //function to round doubles
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    //\\function to round doubles




} //MainActivity


/*available units
----------------------------------------------------------------------------------------------------------------------------------
        1. ml/cc to litres, centilitres \n
        2. cl to ml/cc and l \n
        3. litre to ml/cc, centilitres, gallons \n
        4. gallons to litres \n\n


        5. mm to cm, metres, inches, feet \n
        6. cm to mm, metres, inches, feet \n
        7. dm to mm, cm, m, inches, feet, yards \n
        8. m to dm, cm, km, inches, feet, yards, miles \n
        9. km to m, cm and dm, yards, miles \n
        10. inches to mm, cm, dm, m, feet, yards \n
        11. feet to mm, cm, dm, m, yards, inches \n
        12. yards to cm, dm, m, km, inches, feet \n
        13. miles to m, km \n\n


        14. mg to g \n
        15. g to mg, kg, pounds, ounces \n
        16. kg to g, tonnes, pounds, ounces \n
        17. tonnes to kg, pounds, ounces \n
        18. pounds to g, kg, ounces \n
        19. ounces to pounds, g, kg \n\n


        20. sec to min, hr \n
        21. min to sec, hr \n
        22. hr to min, sec, days \n
        23. days to hours, minutes and seconds, weeks, months, years
        24. months to days, years \n\n


        25. celsius to fahrenheit, kelvin \n
        26. fahrenheit to celsius, kelvin \n
        27. kelvin to celsius, fahrenheit \n\n


        28. radians to degrees \n
        29. degrees to radians \n\n

        30. kb to mb \n
        31. mb to kb, gb \n
        32. gb to mb, tb \n
        33. tb to gb \n\n








----------------------------------------------------------------------------------------------------------------------------------
 */