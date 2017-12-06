
package com.example.android.justjava;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)   {
        int price = quantity*5;
        String priceMessage = "Total: $" + price ;
         priceMessage = priceMessage + "\n\nThank you!" ;
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }


    /**
     * המתודה הזו דואגת לשמור על המידע שהוכנס גם כשהופכים את תצוגת הפלאפון או מכבים אותו
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
        displayPrice(quantity*5);
    }
}

