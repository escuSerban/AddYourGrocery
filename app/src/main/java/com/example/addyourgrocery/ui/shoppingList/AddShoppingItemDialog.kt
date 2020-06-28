package com.example.addyourgrocery.ui.shoppingList

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.addyourgrocery.R
import com.example.addyourgrocery.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_item.*

/*
Here we tell our @ShoppingActivity the result of the Dialog
 */
class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) : AppCompatDialog(context)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_item)

        textView_add.setOnClickListener {
            val name = editText_name.text.toString()
            val amount = editText_amount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        textView_cancel.setOnClickListener { cancel() }
    }
}