package com.almapp.ucaccess.fragments


import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v13.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.almapp.ucaccess.R
import com.almapp.ucaccess.lib.models.WebPageFetcher
import com.almapp.ucaccess.lib.models.WebPageGroup
import kotlinx.android.synthetic.main.fragment_pages.view.*
import nl.komponents.kovenant.ui.failUi
import nl.komponents.kovenant.ui.successUi
import org.jetbrains.anko.*


/**
 * A simple [Fragment] subclass.
 */
class PagesFragment : Fragment() {

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        var view =  inflater.inflate(R.layout.fragment_pages, container, false)
        val fetcher = WebPageFetcher("https://almapp.github.io/uc-access/services.json")
        val adapter = PagerAdapter(fragmentManager)

        view.pager.adapter = adapter
        view.tabs.setupWithViewPager(view.pager)

        fetcher.fetch() successUi {
            adapter.groups = it
            adapter.notifyDataSetChanged()
            view.tabs.setupWithViewPager(view.pager)
        } failUi {
            toast("${it.message}")
        }

        return view
    }

    inner class PagerAdapter(val manager: FragmentManager, var groups: List<WebPageGroup> = emptyList()) : FragmentStatePagerAdapter(manager) {

        override fun getCount() : Int = this.groups.size

        override fun getPageTitle(position: Int): CharSequence? = this.groups[position].name

        override fun getItem(position: Int): Fragment? = Page(this.groups[position])

        override fun getItemPosition(item: Any?): Int {
            val page = item as Page
            val position = this.groups.indexOf(page.group)
            return if (position >= 0) position else POSITION_NONE
        }
    }

    inner class Page(val group: WebPageGroup) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            return with(ctx) {
                frameLayout{
                    verticalLayout {
                        textView(group.name)
                    }
                }
            }
        }
    }
}
