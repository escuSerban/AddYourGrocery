@file:Suppress("DEPRECATION")

package com.example.addyourgrocery.ui.shoppingList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.addyourgrocery.R
import com.example.addyourgrocery.data.db.entities.ShoppingItem
import com.example.addyourgrocery.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

/*
Created by escuSerban on 28/06/2020
 */

@Suppress("DEPRECATION")
class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        recyclerView_items.layoutManager = LinearLayoutManager(this)
        recyclerView_items.adapter = adapter
        recyclerView_items.addItemDecoration(DividerItemDecoration(recyclerView_items.context, DividerItemDecoration.VERTICAL))

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        floating_actionButton.setOnClickListener {
            AddShoppingItemDialog(this,
            object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}