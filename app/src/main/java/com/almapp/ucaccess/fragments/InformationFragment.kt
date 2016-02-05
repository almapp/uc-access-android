package com.almapp.ucaccess.fragments


import android.R
import android.app.Fragment
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.TwoLineListItem
import com.balysv.materialripple.MaterialRippleLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

class InformationFragment : Fragment() {

    val FORM = "https://almapp.github.io/uc-access/form"
    val POLICY = "https://almapp.github.io/uc-access/policy"

    // See: https://github.com/Kotlin/anko/blob/master/doc/ADVANCED.md#extending-anko
    fun ViewManager.rippleView() = rippleView {}
    fun ViewManager.rippleView(init: MaterialRippleLayout.() -> Unit) = ankoView({ MaterialRippleLayout(it) }, init)

    fun open(url: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        val meta = activity.packageManager.getPackageInfo(activity.packageName, 0)
        val manifest = activity.packageManager.getApplicationInfo(activity.packageName, PackageManager.GET_META_DATA).metaData

        // TODO: create factory of headers

        return with(ctx) {
            scrollView {
                verticalLayout {

                    textView("¿Quieres que tú página aparezca acá?") {
                        top = sp(12)
                        textColor = Color.GRAY
                        gravity = Gravity.CENTER_VERTICAL
                    }.lparams {
                        leftMargin = sp(16)
                        rightMargin = sp(16)
                        height = sp(48)
                    }

                    rippleView {
                        include<TextView>(R.layout.simple_list_item_1) {
                            text = "Ir al formulario"
                            onClick { open(FORM) }
                        }
                    }


                    textView("Legal") {
                        top = sp(12)
                        textColor = Color.GRAY
                        gravity = Gravity.CENTER_VERTICAL
                    }.lparams {
                        leftMargin = sp(16)
                        rightMargin = sp(16)
                        height = sp(48)
                    }

                    include<TwoLineListItem>(R.layout.simple_list_item_2) {
                        text1.text = "Aplicación no oficial de la PUC"
                        text2.text = "Por y para la comunidad"
                    }

                    rippleView {
                        include<TextView>(R.layout.simple_list_item_1) {
                            text = "Política de privacidad"
                            onClick { open(POLICY) }
                        }
                    }


                    textView("Aplicación") {
                        top = sp(12)
                        textColor = Color.GRAY
                        gravity = Gravity.CENTER_VERTICAL
                    }.lparams {
                        leftMargin = sp(16)
                        rightMargin = sp(16)
                        height = sp(48)
                    }

                    include<TwoLineListItem>(R.layout.simple_list_item_2) {
                        text1.text = "Versión"
                        text2.text = meta.versionName
                    }

                    include<TwoLineListItem>(R.layout.simple_list_item_2) {
                        text1.text = "Licencia"
                        text2.text = manifest.getString("license")
                    }

                    rippleView {
                        include<TwoLineListItem>(R.layout.simple_list_item_2) {
                            text1.text = "Repositorio"
                            text2.text = manifest.getString("homepage")
                            onClick { open(manifest.getString("homepage")) }
                        }
                    }


                    textView("Autor") {
                        top = sp(12)
                        textColor = Color.GRAY
                        gravity = Gravity.CENTER_VERTICAL
                    }.lparams {
                        leftMargin = sp(16)
                        rightMargin = sp(16)
                        height = sp(48)
                    }

                    include<TextView>(R.layout.simple_list_item_1) {
                        text = manifest.getString("author.name")
                    }

                    rippleView {
                        include<TwoLineListItem>(R.layout.simple_list_item_2) {
                            text1.text = "Email"
                            text2.text = manifest.getString("author.email")
                            onClick { open("mailto:${manifest.getString("author.email")}") }
                        }
                    }

                    rippleView {
                        include<TwoLineListItem>(R.layout.simple_list_item_2) {
                            text1.text = "URL"
                            text2.text = manifest.getString("author.url")
                            onClick { open(manifest.getString("author.url")) }
                        }
                    }
                }
            }
        }
    }
}
