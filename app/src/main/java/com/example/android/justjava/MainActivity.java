
package com.example.android.justjava;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1 ;
        displayQuantity (quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
       if (quantity != 0) {
           quantity = quantity - 1;
       }
        displayQuantity (quantity);
    }

    /**
     * Calculates the price of the order.
     *@return total price
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)   {
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolate0 = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean Chocolate1 = chocolate0.isChecked();
        Log.v("MainActivity", "Has chocolate: "+ Chocolate1);
        int prices = calculatePrice();
        String PriceMessage = createOrderSummary(prices, hasWhippedCream, Chocolate1);
        displayMessage(PriceMessage);
    }

        /**
         * This method creates an Summary of the order.
         * @param price of the order
         * @param addWhippedCream is whether or not the user wants whipped cream topping
         * @param Chocolate2 is whether or not the user wants chocolate topping
         * @return text summary
         */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean Chocolate2){
        String priceMessage1 = "Name: Noa";
            priceMessage1 += "\nAdd whipped cream? "+ addWhippedCream;
            priceMessage1 += "\nAdd chocolate? "+ Chocolate2;
            priceMessage1 += "\nQuantity: " + quantity;
            priceMessage1 += "\nTotal: $" + price;
            priceMessage1 += "\n\nThank you!";

        return priceMessage1;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


    /**
     * keeps info after rotation
     */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("quantity",quantity);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        quantity=savedInstanceState.getInt("quantity",0);
        displayQuantity(quantity);
    }

    /** don't forget to add "order summary" message after rotation
     */
}

