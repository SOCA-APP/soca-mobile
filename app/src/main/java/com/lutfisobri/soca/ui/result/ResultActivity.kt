package com.lutfisobri.soca.ui.result

import com.bumptech.glide.Glide
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityResultBinding
import com.lutfisobri.soca.ui.BaseActivity

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    private val viewModel by lazy { ResultViewModel(AuthPreference(this)) }

    override fun init() {
        val menu = appBar(getString(R.string.result), R.menu.menu_result).menu

        var item = getArgs<HistoryResponseResult>()
        val star = menu.findItem(R.id.menu_favorite)
        star.setOnMenuItemClickListener {
            if (item != null) {
                if (item!!.isFavorite) {
                    viewModel.removeFavorite(item!!.id)
                    viewModel.detail(item!!.id)
                } else {
                    viewModel.addFavorite(item!!.id)
                    viewModel.detail(item!!.id)
                }
            }
            true
        }

        if (item != null) {
            viewModel.detail(item.id)
            viewModel.detail.observe(this@ResultActivity) { detail ->
                item = detail.data
                star.setIcon(setIcon(detail.data.isFavorite))
                setResult(detail.data)
            }
        }
    }

    private fun setIcon(isFavorite: Boolean): Int {
        return if (isFavorite) {
            R.drawable.star_filled
        } else {
            R.drawable.star_outline
        }
    }

    private fun setResult(result: HistoryResponseResult) {
        binding.tvResult.text = result.label
        Glide.with(this)
            .load(result.image)
            .into(binding.imgResult)
    }
}