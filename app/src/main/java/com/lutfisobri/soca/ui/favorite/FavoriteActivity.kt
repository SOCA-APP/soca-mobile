package com.lutfisobri.soca.ui.favorite

import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityFavoriteBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.result.ResultActivity
import com.lutfisobri.soca.utils.exceptions.UnauthorizedException
import com.lutfisobri.soca.utils.gone
import com.lutfisobri.soca.utils.visible

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {
    private val viewModel by lazy { FavoriteViewModel(AuthPreference(this)) }

    override fun init() {
        appBar(getString(R.string.favorite))
        viewModel.favorite()

        val adapter = getAdapter()

        viewModel.favorite.observe(this) {
            adapter.submitData(lifecycle, it)
            adapter.addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.NotLoading) {
                    binding.progressBar.gone()
                    if (adapter.itemCount < 1) {
                        binding.tvNoData.visible()
                    }
                } else if (loadState.refresh is LoadState.Error) {
                    val error = loadState.refresh as LoadState.Error
                    if (error.error is UnauthorizedException) {
                        handleError((error.error as UnauthorizedException).response)
                    }
                }
            }
        }

        binding.rvHistory.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.favorite()
    }

    private fun circularProgressDrawable(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 100f
        circularProgressDrawable.setColorSchemeColors(R.color.blue)
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    private fun getAdapter(): FavoriteAdapter {
        val adapter = FavoriteAdapter(circularProgressDrawable())
        adapter.onItemClick = { item ->
            navTo(ResultActivity::class.java, item)
        }

        return adapter
    }
}