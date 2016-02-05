package com.almapp.ucaccess.fragments


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almapp.ucaccess.R


/**
 * A simple [Fragment] subclass.
 */
class PagesFragment : Fragment() {

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        var view =  inflater.inflate(R.layout.fragment_pages, container, false)
        return view
    }
}
