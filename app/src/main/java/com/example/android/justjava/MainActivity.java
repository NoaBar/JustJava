
package com.example.android.justjava;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1 ;
    String[]toAddresses = new String[]{"Business@email.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    /**
     * This method is called when the plus button is clicked.
     */

    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, R.string.cant100, Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }

            quantity = quantity + 1;
         displayQuantity (quantity);
    }


    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
       if (quantity == 1) {
           // Show an error message as a toast
           Toast.makeText(this, R.string.cant1, Toast.LENGTH_SHORT).show();
           // Exit this method early because there's nothing left to do
           return;
       }
           quantity = quantity - 1;
        displayQuantity (quantity);

    }




    /**
     * Calculates the price of the order.
     *@return total price
     * @param hasWhippedCream is whether or not the user wants cream.
     * @param Chocolate1  is whether or not the user wants chocolate.
     */

    private int calculatePrice(boolean hasWhippedCream, boolean Chocolate1) {
        //price of 1 cup of coffee
        int basePrice = 5;

        //add 1$ if the user wants cream
        if (hasWhippedCream) {
            basePrice = basePrice + 1;
        }

        //add 2$ if the user wants cream
        if (Chocolate1) {
            basePrice = basePrice + 2;
        }

        //calculate the total price
         return quantity* basePrice;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view)   {
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolate0 = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean Chocolate1 = chocolate0.isChecked();

        EditText nameInput = (EditText)findViewById(R.id.name);
        String name = nameInput.getText().toString();
        Log.v("MainActivity","the name is: "+ name);

        int prices = calculatePrice(hasWhippedCream, Chocolate1);
        String PriceMessage = createOrderSummary(name, prices, hasWhippedCream, Chocolate1);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, toAddresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject)+ " "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, PriceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

        /**
         * This method creates an Summary of the order.
         * @param price of the order
         * @param addWhippedCream is whether or not the user wants whipped cream topping
         * @param Chocolate2 is whether or not the user wants chocolate topping
         * @param name is the name of the user
         * @return text summary
         */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean Chocolate2){
        String priceMessage1 = getString(R.string.name_sum, name);
            priceMessage1 += "\n" + getString(R.string.addCream, addWhippedCream);
            priceMessage1 += "\n" + getString(R.string.addChocolate, Chocolate2);
            priceMessage1 += "\n" + getString(R.string.quantity)+ ": " + quantity;
            priceMessage1 += "\n" + getString(R.string.total)+ " " + NumberFormat.getCurrencyInstance().format(price);
            priceMessage1 += "\n\n" + getString(R.string.thankyou);

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
        quantity=savedInstanceState.getInt("quantity",1);
        displayQuantity(quantity);
    }


}

