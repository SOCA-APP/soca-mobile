package com.lutfisobri.soca.ui.favorite

import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityFavoriteBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.result.ResultActivity

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {
    private val viewModel by lazy { FavoriteViewModel(AuthPreference(this)) }

    override fun init() {
        appBar(getString(R.string.favorite))
        viewModel.favorite()

        val adapter = getAdapter()

        viewModel.favorite.observe(this) {
            adapter.submitData(lifecycle, it)
        }

        binding.rvHistory.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.favorite()
    }

    private fun getAdapter(): FavoriteAdapter {
        val adapter = FavoriteAdapter()
        adapter.onItemClick = { item ->
            navTo(ResultActivity::class.java, item)
        }

        return adapter
    }
}