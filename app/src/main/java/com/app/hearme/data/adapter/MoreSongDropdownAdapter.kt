package com.app.hearme.data.adapter

import android.annotation.SuppressLint
import com.app.hearme.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.app.hearme.model.MoreSong


class MoreSongDropdownAdapter(
    private val context: Context,
    private val data: ArrayList<MoreSong>,
    private val type: Int
) :
    BaseAdapter() {
    private lateinit var txtName: TextView
    private lateinit var image: ImageView
    private lateinit var container: LinearLayout
    private lateinit var bottomLine: View

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.view_more_song, parent, false)

        txtName = view.findViewById(R.id.name_dropdown)
        image = view.findViewById(R.id.image_dropdown)
        container = view.findViewById(R.id.container_item_dropdown)
        bottomLine = view.findViewById(R.id.view_bottom_line_item)

        txtName.text = data[position].name
        image.setImageResource(data[position].image)

        if (position == 0) {
            visibleView(view, View.GONE)
        }

        if (position == 9) {
            bottomLine.visibility = View.GONE
        }

        if (type == 0) {
            if (position == 7 || position == 8) {
                visibleView(view, View.GONE)
            }
        } else if (type == 1) {
            if (position == 1 || position == 2 || position == 4 || position == 7 || position == 8) {
                visibleView(view, View.GONE)
            }
        } else if (type == 2) {
            if (position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6) {
                visibleView(view, View.GONE)
            }
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return data[position];
    }

    override fun getCount(): Int {
        return data.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private fun visibleView(view: View, isVisible: Int) {
        txtName.visibility = isVisible
        image.visibility = isVisible
        view.visibility = isVisible
        container.visibility = isVisible
        bottomLine.visibility = isVisible
    }
}