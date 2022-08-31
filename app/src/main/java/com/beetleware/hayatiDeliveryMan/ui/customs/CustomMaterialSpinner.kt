package com.beetleware.hayatiDeliveryMan.ui.customs

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView
import androidx.core.content.res.ResourcesCompat
import com.beetleware.hayatiDeliveryMan.R
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner

class CustomMaterialSpinner : MaterialBetterSpinner {

    var isRequired = false
    var requiredErrorMsg: String? = null


    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style) {
        init(attrs)
    }

    init {
        super.setAccentTypeface(ResourcesCompat.getFont(context, R.font.almarai_regular))
        super.setTypeface(ResourcesCompat.getFont(context, R.font.almarai_regular))
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomMaterialEditText)

        isRequired =
            typedArray.getBoolean(R.styleable.CustomMaterialEditText_required, false)
        requiredErrorMsg = typedArray.getString(R.styleable.CustomMaterialEditText_required_error_msg)

        typedArray.recycle()
    }

    fun validateContent(): Boolean {

        if (isRequired && position == ListView.INVALID_POSITION) {
            if (requiredErrorMsg == null)
                this.error = "${context.resources.getString(R.string.empty_error_msg)} ${hint.toString().replace(
                    "*",
                    ""
                ).trim()}"
            else
                this.error = requiredErrorMsg

            return false
        } else if (!isRequired && this.text.isNullOrEmpty())
            return true


        return true
    }

}