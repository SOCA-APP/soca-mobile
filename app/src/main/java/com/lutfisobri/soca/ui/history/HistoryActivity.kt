package com.lutfisobri.soca.ui.history

import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.models.ItemResultModel
import com.lutfisobri.soca.databinding.ActivityHistoryBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.result.ResultActivity

class HistoryActivity : BaseActivity<ActivityHistoryBinding>() {
    override fun init() {
        val items = listOf(
            ItemResultModel(
                icon = R.drawable.ic_launcher_background,
                title = "History 1",
                isFavorite = true
            ),
            ItemResultModel(
                icon = R.drawable.ic_launcher_background,
                title = "History 2",
                isFavorite = true
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

        binding.rvHistory.apply {
            adapter = getAdapter(items)
        }

        appBar(getString(R.string.history))
    }

    private fun getAdapter(items: List<ItemResultModel>): HistoryAdapter {
        val adapter = HistoryAdapter(items)
        adapter.onItemClick = { item ->
            navTo(ResultActivity::class.java, item)
        }

        return adapter
    }
}