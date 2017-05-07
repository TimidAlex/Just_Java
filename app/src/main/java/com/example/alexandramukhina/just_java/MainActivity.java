package com.example.alexandramukhina.just_java; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;
import static com.example.alexandramukhina.just_java.R.id.checkBox;
import static com.example.alexandramukhina.just_java.R.id.checkBoxChoco;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_names);
// Get the string array
        String[] names = getResources().getStringArray(R.array.names);
// Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        textView.setAdapter(adapter);
    }



    /**
     * This method is called when the order button is clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitOrder(View view) {

        boolean hasChechChoko = checkChoco();
        boolean hasCheckTopping = checkState();

        String summary = createOrderSummary(hasChechChoko, hasCheckTopping);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        intent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "timidshu@yandex.ru" });
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }

//       displayMessage(summary);



        }





    public String createOrderSummary(boolean hasChechChoko, boolean hasCheckTopping) {


        int priceOfOrder = calculatePrice();


        if (hasChechChoko) {
            priceOfOrder = priceOfOrder + 2;
        }

        if (hasCheckTopping)
            priceOfOrder = priceOfOrder + 1;
        String message = "\n" + getString(R.string.order_summary_name, addName()) + " " + addName();
        message = message + "\n " + getString(R.string.TotalQuantity) + " " +  quantity;
        message = message + "\n" + getString(R.string.TotalOrder) + " $ " + priceOfOrder + "\n" + getString(R.string.AddWhippedCream)+ " " +hasCheckTopping + "\n" + getString(R.string.AddChoko) + " " + hasChechChoko + "\n" + getString(R.string.thank_you);

        return message;
    }


//    public void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//
//    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int num) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @param pricePerCup is the price of one cup of coffee
     * @return the price
     */

    // @return total price
    private int calculatePrice() {
        int pricePerCup = 5;
        int price = quantity * pricePerCup;
        return price;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void increment(View view) {

        Toast toast =  Toast.makeText(getApplicationContext(),
                "Cannot be more 100", Toast.LENGTH_SHORT);
        if (quantity == 100) {
            toast.show();
return;
        }
        else   quantity = quantity + 1;
            displayQuantity(quantity);

        }









    @RequiresApi(api = Build.VERSION_CODES.N)
    public void decrement(View view) {

        Toast toast =  Toast.makeText(getApplicationContext(),
                "Cannot be less then 1", Toast.LENGTH_LONG);
        if (quantity > 1 ) {
        quantity = quantity - 1;
        displayQuantity(quantity); }
        else toast.show();

    }


    public boolean checkState() {
        CheckBox checkTopping = (CheckBox) findViewById(checkBox);

     boolean hasCheckTopping = checkTopping.isChecked();


        return  hasCheckTopping;



    }

    public boolean checkChoco () {

        CheckBox checkToppingChoco = (CheckBox) findViewById(checkBoxChoco);
        boolean hasChechChoko = checkToppingChoco.isChecked();
        return  hasChechChoko;
    }

    public String addName () {
AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_names) ;
        String nameString = textView.getText().toString().trim();
        return nameString;
//        EditText textName = (EditText) findViewById(R.id.textName);
//        textName.setText("Google is your friend.", TextView.BufferType.EDITABLE);


    }
}