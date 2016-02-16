package com.almapp.ucaccess.fragments


import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.support.v13.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.almapp.ucaccess.R
import com.almapp.ucaccess.lib.models.WebPageCategory
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater.inflate(R.layout.fragment_pages, container, false)
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

        override fun getCount(): Int = this.groups.size

        override fun getPageTitle(position: Int): CharSequence? = this.groups[position].name

        override fun getItem(position: Int): Fragment? = MyPage(this.groups[position])

        override fun getItemPosition(item: Any?): Int {
            val page = item as MyPage
            val position = this.groups.indexOf(page.group)
            return if (position >= 0) position else POSITION_NONE
        }
    }

    inner class MyPage(val group: WebPageGroup) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            return with(ctx) {
                frameLayout {
                    expandableListView() {
                        setAdapter(MyListAdapter(context, group.categories))
                        group.categories.forEachIndexed { i, category -> expandGroup(i) } // Start expanded
                    }
                }
            }
        }
    }

    inner class MyListAdapter(val context: Context, var categories: List<WebPageCategory> = emptyList()) : BaseExpandableListAdapter() {

        override fun getGroup(groupPosition: Int): Any? = this.categories[groupPosition]
        override fun getChild(groupPosition: Int, childPosition: Int): Any? = this.categories[groupPosition].webpages[childPosition]

        override fun getChildrenCount(groupPosition: Int): Int = this.categories[groupPosition].webpages.size
        override fun getGroupCount(): Int = this.categories.size

        override fun getGroupId(childPosition: Int): Long = childPosition.toLong()
        override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

        override fun hasStableIds(): Boolean = false
        override fun isChildSelectable(p0: Int, p1: Int): Boolean = true

        override fun getGroupView(groupPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
            val category = this.categories[groupPosition]

            val view = convertView ?: with(this.context) {
                verticalLayout {
                    textView {
                        id = 100
                    }
                }
            }

            view.find<TextView>(id = 100).text = category.name
            return view
        }

        override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
            val webpage = this.categories[groupPosition].webpages[childPosition]

            val view = convertView ?: with(this.context) {
                verticalLayout {
                    textView {
                        id = 100
                    }
                }
            }

            view.find<TextView>(id = 100).text = webpage.name
            return view
        }
    }
}
