package com.almapp.ucaccess

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    enum class Views {
        PAGES, ACCOUNTS, INFORMATION,
    }

    /**
     * Navigation drawer reference
     */
    var result: Drawer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup toolbar
        setSupportActionBar(this.toolbar)

        // Set initialfragment
        fragmentManager.beginTransaction().replace(R.id.frame_container, this.fragmentFactory(Views.PAGES)).commit()

        // Build navigation drawer
        this.result = DrawerBuilder(this)
                .withActivity(this)
                .withRootView(this.drawer_container)
                .withToolbar(toolbar)
                .withAccountHeader(
                        AccountHeaderBuilder()
                                .withActivity(this)
                                .withHeaderBackground(R.drawable.header)
                                .addProfiles(
                                        ProfileDrawerItem().withName("Accesos UC").withEmail("username@uc.cl").withIcon(R.drawable.icon)
                                )
                                .build()
                )
                .withActionBarDrawerToggleAnimated(true)
                .addDrawerItems(
                        PrimaryDrawerItem().withName("Páginas").withIcon(Ionicons.Icon.ion_ios_book_outline).withIdentifier(Views.PAGES.ordinal),
                        PrimaryDrawerItem().withName("Cuentas").withIcon(Ionicons.Icon.ion_android_person).withIdentifier(Views.ACCOUNTS.ordinal)
                )
                .addStickyDrawerItems(
                        PrimaryDrawerItem().withName("Información").withIcon(Ionicons.Icon.ion_information_circled).withIdentifier(Views.INFORMATION.ordinal)
                )
                .withOnDrawerItemClickListener { view, position, item ->
                    Log.d("NAV", "Selected position ${position.toString()} and id: ${item.identifier}")
                    when (item.identifier) {
                        Views.PAGES.ordinal -> {
                            fragmentManager.beginTransaction().replace(R.id.frame_container, this.fragmentFactory(Views.PAGES)).commit()
                        }
                        Views.ACCOUNTS.ordinal -> {
                            fragmentManager.beginTransaction().replace(R.id.frame_container, this.fragmentFactory(Views.ACCOUNTS)).commit()
                        }
                        Views.INFORMATION.ordinal -> {
                            fragmentManager.beginTransaction().replace(R.id.frame_container, this.fragmentFactory(Views.INFORMATION)).commit()
                        }
                    }
                    false
                }
                .withSavedInstance(savedInstanceState)
                .build()
    }

    fun fragmentFactory(view: Views): Fragment {
        when (view) {
            Views.PAGES -> return PagesFragment()
            Views.ACCOUNTS -> return PagesFragment()
            Views.INFORMATION -> return InformationFragment()
            else -> return PagesFragment()
        }
    }

    override fun onBackPressed() {
        if (this.result?.isDrawerOpen as Boolean) {
            this.result!!.closeDrawer()
        } else {
            super.onBackPressed()
        }
    }
}
