package com.example.alexandramukhina.just_java; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static android.R.id.message;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitOrder(View view) {
          String summary = createOrderSummary();
        displayMessage(summary);



    }

    public String createOrderSummary () {
      int priceOfOrder = calculatePrice ();
        String message = "\nName: Alexandra Mukhina" ;
        message = message + "\nQuantity " + quantity ;
        message = message  + "\nTotal: " + priceOfOrder + " Thank you!";

                return message;





    }

    public void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);



    }

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
     * @return the price
     * @param pricePerCup is the price of one cup of coffee
     */

    // @return total price
    private int calculatePrice () {
        int pricePerCup = 5 ;
       int price = quantity * pricePerCup;
        return price;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void increment(View view) {


        quantity = quantity + 1;
        displayQuantity(quantity);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void decrement(View view) {


        quantity = quantity - 1;
        displayQuantity(quantity);

    }

}