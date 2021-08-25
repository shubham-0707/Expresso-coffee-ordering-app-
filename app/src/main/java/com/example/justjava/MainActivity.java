package com.example.justjava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.NumberFormat;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends Activity {
    int coffeeCount = 1;
    String username = " ";
    int basePrice = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/**
 * This method is used to get the name of the person
 */
public void getName()
{
    EditText getNaam = (EditText) findViewById(R.id.naam_batao);
    String str = getNaam.getText().toString();
    username = str;
}
    /**
     * This method is called when the order button is clicked ....
     */
    public void aisehi(View view) {
        getName();
        displayCheckedState();
    }

    /**
     * This method is used to print the summary of the order ....
     */
    public void Order_summary(int basePrice, boolean check, boolean checkc) {
        displayPrice(coffeeCount*basePrice);
        String text = "Name : " + username;
        text += "\nQuantity : " + coffeeCount;
        text += "\nAdded Whipped Cream ? : " + check;
        text+= "\nAdded Chocolate Topping ? : " + checkc;
        text += "\nPrice : $" + (coffeeCount * basePrice );
        text += "\n" + getString(R.string.thank_you);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Summary for " + username);
        intent.putExtra(Intent.EXTRA_TEXT , text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /**
     * This method increases the value of CoffeeCount by 1.....
     *
     * @param view
     */
    public void increment(View view) {
        if(coffeeCount==100)
        {
            Toast.makeText(getApplicationContext() , "Can't Order more than 100 cups of coffee" , Toast.LENGTH_SHORT).show();
            return;
        }
        coffeeCount = coffeeCount + 1;
        displayQuantity(coffeeCount);
    }

    /**
     * This method decreases the value of CoffeeCount by 1....
     *
     * @param view
     */
    public void decrement(View view) {
        if(coffeeCount==1)
        {
            Toast.makeText(getApplicationContext() , "Can't order less than 1 cup of Coffee" , Toast.LENGTH_SHORT).show();
            return;
        }
        coffeeCount = coffeeCount - 1;
        displayQuantity(coffeeCount);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }



    /**
     * This method is used to return the checked state of a CheckBox.....
     *
     * @return
     */
    public void displayCheckedState() {
        CheckBox check_cream = (CheckBox) findViewById(R.id.check_id);
        boolean check = check_cream.isChecked();
        CheckBox check_chocolate = (CheckBox) findViewById(R.id.check_id_chocolate);
        boolean checkc = check_chocolate.isChecked();
        if(check==true)
        {
            basePrice= basePrice +1;
        }
        if(checkc==true)
        {
            basePrice = basePrice + 2;
        }
        Order_summary(basePrice , check , checkc);
    }
}