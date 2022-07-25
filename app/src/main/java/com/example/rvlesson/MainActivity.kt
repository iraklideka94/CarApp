package com.example.rvlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rvlesson.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private var items = ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        items.add(
            Item(
                UUID.randomUUID(),
                "data:image/jpeg;base64,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",
                "BMW",
                "M-8"
            ))

        items.add(
            Item(
                UUID.randomUUID(),
                "https://www.la.mercedes-benz.com/en/passengercars/mercedes-benz-cars/models/e-class/saloon-w213-fl/amg/exterior-design/_jcr_content/par/productinfotabnav/tabnav/productinfotabnavite_1951209224/tabnavitem/videoimageslider/slides/videoimageslide/image.MQ6.12.20220204235806.jpeg",
                "MERCEDES",
                "IMG E class"
            )
        )

        items.add(

            Item(
                UUID.randomUUID(),
                "http://image-cdn.beforward.jp/large/202006/1843252/BH264133_860cff.jpeg",
                "NISSAN",
                "Juke"
            )
        )

        adapter = ItemAdapter(this, object : ItemAdapter.OnItemClickListener{
            override fun onItemLongClick(position: Int) {
            val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setMessage("delete Item ${position +1}?")
                dialog.setNegativeButton("No"){p0, p1 ->
                    p0.cancel()
                }

                dialog.setPositiveButton("Yes"){p0, p1 ->
                    p0.cancel()

                    val item = items[position]
                    val list = ArrayList(items)
                    list.remove(item)
                    items = list
                    adapter.submitList(list)
                }
                dialog.show()
            }
        })
        adapter.submitList(items)

        binding.recyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@MainActivity)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            hasFixedSize()
            adapter = this@MainActivity.adapter

        }
    }
}