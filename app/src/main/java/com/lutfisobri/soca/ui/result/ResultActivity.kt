package com.lutfisobri.soca.ui.result

import android.annotation.SuppressLint
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.models.ItemResultModel
import com.lutfisobri.soca.databinding.ActivityResultBinding
import com.lutfisobri.soca.ui.BaseActivity

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun init() {
        val menu = appBar(getString(R.string.result), R.menu.menu_result).menu

        val item = getArgs<ItemResultModel>()
        menu.findItem(R.id.menu_favorite).apply {
            if (item != null) {
                icon = if (item.isFavorite) getDrawable(R.drawable.star_filled) else getDrawable(R.drawable.star_outline)
            }
            setOnMenuItemClickListener {
                if (item != null) {
                    if (item.isFavorite) {
                        item.isFavorite = false
                        setIcon(R.drawable.star_outline)
                    } else {
                        item.isFavorite = true
                        setIcon(R.drawable.star_filled)
                    }
                }
                true
            }
        }

        with(binding) {
            if (item != null) {
                imgResult.setImageResource(item.icon)
                tvResult.text = item.title
            }
        }
    }
}