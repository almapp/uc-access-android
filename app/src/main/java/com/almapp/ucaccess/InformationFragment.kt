package com.almapp.ucaccess


import android.app.Fragment
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.TwoLineListItem
import com.balysv.materialripple.MaterialRippleLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

class InformationFragment : Fragment() {

    val FORM = "https://almapp.github.io/uc-access/form"
    val POLICY = "https://almapp.github.io/uc-access/policy"

    // See: https://github.com/Kotlin/anko/blob/master/doc/ADVANCED.md#extending-anko
    public inline fun ViewManager.rippleView() = rippleView {}
    public inline fun ViewManager.rippleView(init: MaterialRippleLayout.() -> Unit) = ankoView({ MaterialRippleLayout(it) }, init)

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        return with(ctx) {
            scrollView {
                verticalLayout {
                    rippleView {
                        include<TwoLineListItem>(android.R.layout.simple_list_item_2) {
                            text1.text = "¿Quieres que tú página aparezca acá?"
                            text2.text = "Ir al formulario"
                            onClick {
                                open(FORM)
                            }
                        }
                    }

                    include<TwoLineListItem>(android.R.layout.simple_list_item_2) {
                        text1.text = "Aplicación no oficial de la PUC"
                        text2.text = "Por y para la comunidad"
                    }


                    rippleView {
                        include<TwoLineListItem>(android.R.layout.simple_list_item_2) {
                            text1.text = "Política de privacidad"
                            text2.text = "Ver"

                            onClick {
                                open(POLICY)
                            }
                        }
                    }
                }
            }
        }
    }

    fun open(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
