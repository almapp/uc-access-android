package com.almapp.ucaccess

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mikepenz.ionicons_typeface_library.Ionicons
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var result: Drawer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup toolbar
        setSupportActionBar(this.toolbar)

        // Build navigation drawer
        this.result = DrawerBuilder(this)
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
                        PrimaryDrawerItem().withName("Páginas").withIcon(Ionicons.Icon.ion_ios_book_outline),
                        PrimaryDrawerItem().withName("Cuentas").withIcon(Ionicons.Icon.ion_android_person)
                )
                .addStickyDrawerItems(PrimaryDrawerItem().withName("Información").withIcon(Ionicons.Icon.ion_information_circled))
                .withSavedInstance(savedInstanceState)
                .build()
    }

    override fun onBackPressed() {
        if (this.result?.isDrawerOpen as Boolean) {
            this.result!!.closeDrawer()
        } else {
            super.onBackPressed()
        }
    }
}
