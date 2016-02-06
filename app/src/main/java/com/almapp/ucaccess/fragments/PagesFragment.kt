package com.almapp.ucaccess.fragments


import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almapp.ucaccess.R
import org.jetbrains.anko.*
import kotlinx.android.synthetic.main.fragment_pages.view.*


/**
 * A simple [Fragment] subclass.
 */
class PagesFragment : Fragment() {

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        var view =  inflater.inflate(R.layout.fragment_pages, container, false)

        view.pager.adapter = SamplePagerAdapter(fragmentManager)
        view.tabs.setViewPager(view.pager)

        return view
    }

    inner class SamplePagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        val titles = arrayOf("1", "2")

        override fun getCount() : Int {
            return this.titles.count()
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return this.titles[position]
        }

        override fun getItem(p0: Int): Fragment? {
            return Frag(p0.toString())
        }
    }

    inner class Frag(val text: String) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            return with(ctx) {
                frameLayout{
                    verticalLayout {
                        textView(text)
                    }
                }
            }
        }
    }
}
