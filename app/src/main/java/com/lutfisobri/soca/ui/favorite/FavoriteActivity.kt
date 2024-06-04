package com.lutfisobri.soca.ui.favorite

import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.models.ItemResultModel
import com.lutfisobri.soca.databinding.ActivityFavoriteBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.result.ResultActivity

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {
    override fun init() {
        val items = listOf(
            ItemResultModel(
                icon = R.drawable.ic_launcher_background,
                title = "History 1"
            ),
            ItemResultModel(
                icon = R.drawable.ic_launcher_background,
                title = "History 2"
            ),
            ItemResultModel(
                icon = R.drawable.ic_launcher_background,
                title = "History 3"
            ),
            ItemResultModel(
                icon = R.drawable.ic_launcher_background,
                title = "History 4"
            ),
            ItemResultModel(
                icon = R.drawable.ic_launcher_background,
                title = "History 5"
            ),
        )

        binding.rvHistory.adapter = getAdapter(items)

        appBar(getString(R.string.favorite))
    }

    private fun getAdapter(items: List<ItemResultModel>): FavoriteAdapter {
        val adapter = FavoriteAdapter(items)
        adapter.onItemClick = { item ->
            navTo(ResultActivity::class.java, item)
        }

        return adapter
    }
}