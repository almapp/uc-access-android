package com.almapp.ucaccess

import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.ViewManager
import android.webkit.WebViewClient
import com.almapp.ucaccess.lib.models.WebPage
import com.balysv.materialripple.MaterialRippleLayout
import kotlinx.android.synthetic.main.activity_webbrowser.*
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

/**
 * Created by patriciolopez on 28-02-16.
 */

// fun ViewManager.toolBar() = toolBar {}
// fun ViewManager.toolBar(init: Toolbar.() -> Unit) = ankoView({ Toolbar(it) }, init)

class WebBrowserActivity : AppCompatActivity() {

    var webpage: WebPage?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webbrowser)

        // Get items of intent
        this.webpage = intent.getSerializableExtra("webpage") as WebPage

        // Setup toolbar
        setSupportActionBar(this.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        this.toolbar.title = this.webpage!!.name
        this.toolbar.subtitle = this.webpage!!.URL

        // Setup webview
        this.webview.setWebViewClient(WebViewClient())
        this.webview.loadUrl(webpage!!.URL)
        
        /*
        relativeLayout {
            val toolbar = toolBar {
                backgroundColor = R.attr.colorPrimary
                setTheme(R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
                popupTheme = R.style.ThemeOverlay_AppCompat_Light
            }.lparams(height = android.R.attr.actionBarSize, width = matchParent)

            setSupportActionBar(toolbar)

            frameLayout {
                webView {
                    setWebViewClient(WebViewClient())
                    loadUrl(webpage!!.URL)
                }
            }.lparams { below(toolbar) }
        }
        */
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
