package com.lutfisobri.soca.ui.result

import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityResultBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.utils.gone

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    private val viewModel by lazy { ResultViewModel(AuthPreference(this)) }

    override fun init() {
        val menu = appBar(getString(R.string.result), R.menu.menu_result).menu

        var item = getArgs<HistoryResponseResult>()
        val star = menu.findItem(R.id.menu_favorite)
        star.isVisible = false
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
                binding.progressBar.gone()
                star.isVisible = true
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
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 100f
        circularProgressDrawable.setColorSchemeColors(R.color.blue)
        circularProgressDrawable.start()

        binding.tvResult.text = result.label
        Glide.with(this)
            .load(result.image)
            .placeholder(circularProgressDrawable)
            .into(binding.imgResult)
    }
}