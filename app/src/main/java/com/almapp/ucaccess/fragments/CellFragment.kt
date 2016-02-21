package com.almapp.ucaccess.fragments

import android.os.Build
import android.view.Gravity
import android.widget.ImageView
import com.almapp.ucaccess.R
import org.jetbrains.anko.*

/**
 * Created by patriciolopez on 21-02-16.
 */
class CellFragment<T> : AnkoComponent<T> {

    companion object {
        const val IMAGE_ID = 1
        const val TITLE_ID = 2
        const val DETAIL_ID = 3
    }

    override fun createView(ui: AnkoContext<T>) = ui.apply {
        val height = sp(92)
        linearLayout {
            lparams(width = matchParent, height = height)

            imageView(R.drawable.icon) {
                id = CellFragment.IMAGE_ID
            }.lparams(width = height, height = height) {
                topPadding = sp(16)
                rightPadding = sp(16)
                bottomPadding = sp(16)
                gravity = Gravity.CENTER
            }

            verticalLayout {
                textView("Lorem Ipsum") {
                    id = CellFragment.TITLE_ID
                    if (Build.VERSION.SDK_INT < 23) {
                        setTextAppearance(ui.ctx, android.R.style.TextAppearance_Material_Medium)
                    } else {
                        setTextAppearance(android.R.style.TextAppearance_Material_Medium)
                    }
                }

                textView("Dolor alem") {
                    id = CellFragment.DETAIL_ID
                    lines = 2
                    minLines = 2
                    maxLines = 2
                    if (Build.VERSION.SDK_INT < 23) {
                        setTextAppearance(ui.ctx, android.R.style.TextAppearance_Material_Small)
                    } else {
                        setTextAppearance(android.R.style.TextAppearance_Material_Small)
                    }
                }
            }.lparams {
                gravity = Gravity.CENTER
            }
        }
    }.view
}
