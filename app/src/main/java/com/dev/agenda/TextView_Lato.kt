package com.dev.agenda

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView


class TextView_Lato : TextView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        if (!isInEditMode) {
            val tf = Typeface.createFromAsset(context.assets, "fonts/Lato-Medium.ttf")
            typeface = tf
        }
    }

}