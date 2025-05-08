package com.example.pebblesampleapp_new;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.Dialog;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Button exportCSV, addFine, subFine, addSand, subSand, add2mm, sub2mm, add2_8mm, sub2_8mm, add4mm, sub4mm, add5_6mm, sub5_6mm, add8mm, sub8mm, add11mm, sub11mm, add16mm, sub16mm, add22_6mm, sub22_6mm, add32mm, sub32mm, add45mm, sub45mm, add64mm, sub64mm, add90mm, sub90mm, add128mm, sub128mm, add180mm, sub180mm, addCus, subCus;

    TextView amountFine, amountSand, amount2mm, amount2_8mm, amount4mm, amount5_6mm, amount8mm, amount11mm, amount16mm, amount22_6mm, amount32mm, amount45mm, amount64mm, amount90mm, amount128mm, amount180mm, amountCus, totalNumber, D16Text, D16Perc, D50Text, D50Perc, D84Text, D84Perc, D95Text, D95Perc;

//    int fineAmt = 0;
//    int sandAmt = 0;
//    int twoAmt = 0;
//    int two_8Amt = 0;
//    int fourAmt = 0;
//    int five_6Amt = 0;
//    int eightAmt = 0;
//    int elevenAmt = 0;
//    int sixteenAmt = 0;
//    int twentyTwo_6Amt = 0;
//    int thirtyTwoAmt = 0;
//    int fortyFiveAmt = 0;
//    int sixtyFourAmt = 0;
//    int ninetyAmt = 0;
//    int oneHundredTwentyEightAmt = 0;
//    int oneHundredEightyAmt = 0;
//    int customAmt = 0;
    int total = 0;

    Map<String, Integer> data = new LinkedHashMap<>();

    //String filepath = "/storage/emulated/0/Download/test.csv";

    String home = System.getProperty("user.home");
    String filename = "WolmanData" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date());;
    String filepath = home + "/Downloads/" + filename + ".csv";

    List<Double> customData = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, filepath, Toast.LENGTH_SHORT).show();

        //setting up CSV stuff
        exportCSV = findViewById(R.id.ExportCSV);

        //Initing buttons
        addFine = findViewById(R.id.addFine);
        subFine = findViewById(R.id.subFine);
        addSand = findViewById(R.id.addSand);
        subSand = findViewById(R.id.subSand);
        add2mm = findViewById(R.id.add2mm);
        sub2mm = findViewById(R.id.sub2mm);
        add2_8mm = findViewById(R.id.add2_8mm);
        sub2_8mm = findViewById(R.id.sub2_8mm);
        add4mm = findViewById(R.id.add4mm);
        sub4mm = findViewById(R.id.sub4mm);
        add5_6mm = findViewById(R.id.add5_6mm);
        sub5_6mm = findViewById(R.id.sub5_6mm);
        add8mm = findViewById(R.id.add8mm);
        sub8mm = findViewById(R.id.sub8mm);
        add11mm = findViewById(R.id.add11mm);
        sub11mm = findViewById(R.id.sub11mm);
        add16mm = findViewById(R.id.add16mm);
        sub16mm = findViewById(R.id.sub16mm);
        add22_6mm = findViewById(R.id.add22_6mm);
        sub22_6mm = findViewById(R.id.sub22_6mm);
        add32mm = findViewById(R.id.add32mm);
        sub32mm = findViewById(R.id.sub32mm);
        add45mm = findViewById(R.id.add45mm);
        sub45mm = findViewById(R.id.sub45mm);
        add64mm = findViewById(R.id.add64mm);
        sub64mm = findViewById(R.id.sub64mm);
        add90mm = findViewById(R.id.add90mm);
        sub90mm = findViewById(R.id.sub90mm);
        add128mm = findViewById(R.id.add128mm);
        sub128mm = findViewById(R.id.sub128mm);
        add180mm = findViewById(R.id.add180mm);
        sub180mm = findViewById(R.id.sub180mm);
        addCus = findViewById(R.id.addCus);
        subCus = findViewById(R.id.subCus);

        //Initing TextViews
        amountFine = findViewById(R.id.amountFine);
        amountSand = findViewById(R.id.amountSand);
        amount2mm = findViewById(R.id.amount2mm);
        amount2_8mm = findViewById(R.id.amount2_8mm);
        amount4mm = findViewById(R.id.amount4mm);
        amount5_6mm = findViewById(R.id.amount5_6mm);
        amount8mm = findViewById(R.id.amount8mm);
        amount11mm = findViewById(R.id.amount11mm);
        amount16mm = findViewById(R.id.amount16mm);
        amount22_6mm = findViewById(R.id.amount22_6mm);
        amount32mm = findViewById(R.id.amount32mm);
        amount45mm = findViewById(R.id.amount45mm);
        amount64mm = findViewById(R.id.amount64mm);
        amount90mm = findViewById(R.id.amount90mm);
        amount128mm = findViewById(R.id.amount128mm);
        amount180mm = findViewById(R.id.amount180mm);
        amountCus = findViewById(R.id.amountCus);
        D16Text = findViewById(R.id.D16Text);
        D16Perc = findViewById(R.id.D16Perc);
        D50Text = findViewById(R.id.D50Text);
        D50Perc = findViewById(R.id.D50Perc);
        D84Text = findViewById(R.id.D84Text);
        D84Perc = findViewById(R.id.D84Perc);
        D95Text = findViewById(R.id.D95Text);
        D95Perc = findViewById(R.id.D95Perc);

        totalNumber = findViewById(R.id.totalNumber);

        //Init Data hashmap
        data.put("0.01", 0);
        data.put("0.5", 0);
        data.put("2", 0);
        data.put("2.8", 0);
        data.put("4", 0);
        data.put("5.6", 0);
        data.put("8", 0);
        data.put("11", 0);
        data.put("16", 0);
        data.put("22.6", 0);
        data.put("32", 0);
        data.put("45", 0);
        data.put("64", 0);
        data.put("90", 0);
        data.put("128", 0);
        data.put("180", 0);
        data.put("Custom", 0);

        //On clicks
        exportCSV.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Map<String, Integer> categoryAmounts = new LinkedHashMap<>();
                 //putDataInHash(categoryAmounts, data);
                 writeDataToCSV(filepath, data, customData);
             }
         });

        addFine.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               data.put("0.01", data.get("0.01") + 1);
               total++;
               totalNumber.setText(String.valueOf(total));
               amountFine.setText(String.valueOf(data.get("0.01")));
               displayPercentiles(data, customData);
           }

       });
        subFine.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(data.get("0.01") > 0)
               {
                   data.put("0.01", data.get("0.01") - 1);
                   total--;
                   totalNumber.setText(String.valueOf(total));
                   amountFine.setText(String.valueOf(data.get("0.01")));
                   displayPercentiles(data, customData);
               }
           }
       });

        addSand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("0.5", data.get("0.5") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amountSand.setText(String.valueOf(data.get("0.5")));
                displayPercentiles(data, customData);
            }
        });
        subSand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("0.5") > 0)
                {
                    data.put("0.5", data.get("0.5") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amountSand.setText(String.valueOf(data.get("0.5")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add2mm.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              data.put("2", data.get("2") + 1);
              total++;
              totalNumber.setText(String.valueOf(total));
              amount2mm.setText(String.valueOf(data.get("2")));
              displayPercentiles(data, customData);
          }
        });
        sub2mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("2") > 0)
                {
                    data.put("2", data.get("2") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount2mm.setText(String.valueOf(data.get("2")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add2_8mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("2.8", data.get("2.8") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount2_8mm.setText(String.valueOf(data.get("2.8")));
                displayPercentiles(data, customData);
            }
        });
        sub2_8mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("2.8") > 0)
                {
                    data.put("2.8", data.get("2.8") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount2_8mm.setText(String.valueOf(data.get("2.8")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add4mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("4", data.get("4") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount4mm.setText(String.valueOf(data.get("4")));
                displayPercentiles(data, customData);
            }
        });
        sub4mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("4") > 0)
                {
                    data.put("4", data.get("4") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount4mm.setText(String.valueOf(data.get("4")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add5_6mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("5.6", data.get("5.6") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount5_6mm.setText(String.valueOf(data.get("5.6")));
                displayPercentiles(data, customData);
            }
        });
        sub5_6mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("5.6") > 0) {
                    data.put("5.6", data.get("5.6") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount5_6mm.setText(String.valueOf(data.get("5.6")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add8mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("8", data.get("8") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount8mm.setText(String.valueOf(data.get("8")));
                displayPercentiles(data, customData);
            }
        });
        sub8mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("8") > 0)
                {
                    data.put("8", data.get("8") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount8mm.setText(String.valueOf(data.get("8")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add11mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("11", data.get("11") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount11mm.setText(String.valueOf(data.get("11")));
                displayPercentiles(data, customData);
            }
        });
        sub11mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("11") > 0)
                {
                    data.put("11", data.get("11") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount11mm.setText(String.valueOf(data.get("11")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add16mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("16", data.get("16") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount16mm.setText(String.valueOf(data.get("16")));
                displayPercentiles(data, customData);
            }
        });
        sub16mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("16") > 0)
                {
                    data.put("16", data.get("16") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount16mm.setText(String.valueOf(data.get("16")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add22_6mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("22.6", data.get("22.6") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount22_6mm.setText(String.valueOf(data.get("22.6")));
                displayPercentiles(data, customData);
            }
        });
        sub22_6mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("22.6") > 0)
                {
                    data.put("22.6", data.get("22.6") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount22_6mm.setText(String.valueOf(data.get("22.6")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add32mm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               data.put("32", data.get("32") + 1);
               total++;
               totalNumber.setText(String.valueOf(total));
               amount32mm.setText(String.valueOf(data.get("32")));
               displayPercentiles(data, customData);
           }
       });
        sub32mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("32") > 0)
                {
                    data.put("32", data.get("32") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount32mm.setText(String.valueOf(data.get("32")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add45mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("45", data.get("45") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount45mm.setText(String.valueOf(data.get("45")));
                displayPercentiles(data, customData);
            }
        });
        sub45mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("45") > 0)
                {
                    data.put("45", data.get("45") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount45mm.setText(String.valueOf(data.get("45")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add64mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("64", data.get("64") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount64mm.setText(String.valueOf(data.get("64")));
                displayPercentiles(data, customData);
                }
        });
        sub64mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("64") > 0)
                {
                    data.put("64", data.get("64") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount64mm.setText(String.valueOf(data.get("64")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add90mm.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   data.put("90", data.get("90") + 1);
                   total++;
                   totalNumber.setText(String.valueOf(total));
                   amount90mm.setText(String.valueOf(data.get("90")));
                   displayPercentiles(data, customData);
               }
           });
        sub90mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("90") > 0)
                {
                    data.put("90", data.get("90") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount90mm.setText(String.valueOf(data.get("90")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add128mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("128", data.get("128") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount128mm.setText(String.valueOf(data.get("128")));
                displayPercentiles(data, customData);
            }
        });
        sub128mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("128") > 0)
                {
                    data.put("128", data.get("128") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount128mm.setText(String.valueOf(data.get("128")));
                    displayPercentiles(data, customData);
                }
            }
        });

        add180mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("180", data.get("180") + 1);
                total++;
                totalNumber.setText(String.valueOf(total));
                amount180mm.setText(String.valueOf(data.get("180")));
                displayPercentiles(data, customData);
            }
        });
        sub180mm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("180") > 0)
                {
                    data.put("180", data.get("180") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amount180mm.setText(String.valueOf(data.get("180")));
                    displayPercentiles(data, customData);
                }
            }
        });

        addCus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  //Create alert builder
                  AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                  builder.setTitle("Enter custom amount");

                  //set custom layout
                  LayoutInflater inflater = getLayoutInflater();
                  View customLayout = inflater.inflate(R.layout.custom_amt_popup, null);
                  builder.setView(customLayout);

                  //Add a Positive button
                  builder.setPositiveButton("OK", (dialog, which) -> {
                              EditText amountEditText = customLayout.findViewById(R.id.amountEditText);
                              //Toast.makeText(MainActivity.this, "Amount: " + amountEditText.getText(), Toast.LENGTH_SHORT).show();
                              data.put("Custom", data.get("Custom") + 1);
                              total++;
                              totalNumber.setText(String.valueOf(total));
                              amountCus.setText(String.valueOf(data.get("Custom")));
                              customData.add(Double.parseDouble(amountEditText.getText().toString()));
                              displayPercentiles(data, customData);
                          });
                  //Add a Negative button
                  builder.setNegativeButton("Cancel", (dialog, which) -> {
                      dialog.cancel();
                  });

                  AlertDialog dialog = builder.create();
                  dialog.show();
              }
          });
        subCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get("Custom") > 0)
                {
                    data.put("Custom", data.get("Custom") - 1);
                    total--;
                    totalNumber.setText(String.valueOf(total));
                    amountCus.setText(String.valueOf(data.get("Custom")));
                    customData.remove(customData.size() - 1);
                    displayPercentiles(data, customData);
                }
            }
        });


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

    public void writeDataToCSV(String filePath, Map<String, Integer> data, List<Double> customData)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // write data to file
            for(Map.Entry<String, Integer> entry : data.entrySet())
            {
                String category = entry.getKey();
                int amount = entry.getValue();

                if(category.equals("Custom"))
                {
                    for(int i = 0; i < amount; i++)
                    {
                        writer.writeNext(new String[] {customData.get(i).toString()});
                    }
                }
                else
                {
                    for(int i = 0; i < amount; i++)
                    {
                        writer.writeNext(new String[] {category});
                    }
                }


            }
            // closing writer connection
            writer.close();

            //Print success message
            Toast.makeText(MainActivity.this, "CSV file created", Toast.LENGTH_SHORT).show();
        }
        catch (FileNotFoundException e)
        {
            Toast.makeText(MainActivity.this, "Tried to save file to " + filePath +
                    " but location does not exist", Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error occurred: CSV file not created", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private void displayPercentiles(Map<String, Integer> data, List<Double> customData)
    {
        double D16 = 0;
        double D50 = 0;
        double D84 = 0;
        double D95 = 0;

        int index = (int) Math.ceil((16.0/100.0) * total);
        D16Perc.setText(getIndex(data, customData, index));
        index = (int) Math.ceil((50.0/100.0) * total);
        D50Perc.setText(getIndex(data, customData, index));
        index = (int) Math.ceil((84.0/100.0) * total);
        D84Perc.setText(getIndex(data, customData, index));
        index = (int) Math.ceil((95.0/100.0) * total);
        D95Perc.setText(getIndex(data, customData, index));
    }


    private String getIndex(Map<String, Integer> data, List<Double> customData, int index)
    {
        if(index <=0)
        {
            return "0";
        }

        for(Map.Entry<String, Integer> entry : data.entrySet())
        {
            index = index - entry.getValue();
            if(index <= 0)
            {
                if(entry.getKey().equals("Custom"))
                {
                    index += entry.getValue();
                    return customData.get(index - 1).toString();
                }
                else {
                    return entry.getKey();
                }
            }

        }
        return "0";
    }

}