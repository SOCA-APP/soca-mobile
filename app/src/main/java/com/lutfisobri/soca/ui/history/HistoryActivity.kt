package com.lutfisobri.soca.ui.history

import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.databinding.ActivityHistoryBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.result.ResultActivity
import com.lutfisobri.soca.utils.gone
import com.lutfisobri.soca.utils.visible

class HistoryActivity : BaseActivity<ActivityHistoryBinding>() {
    private val viewModel by lazy { HistoryViewModel(AuthPreference(this)) }

    override fun init() {
        appBar(getString(R.string.history))
        viewModel.history()

        val adapter = getAdapter()

        viewModel.history.observe(this) {
            adapter.submitData(lifecycle, it)
            adapter.addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.NotLoading) {
                    binding.progressBar.gone()
                    if (adapter.itemCount < 1) {
                        binding.tvNoData.visible()
                    }
                }
            }
        }

        binding.rvHistory.adapter = adapter
    }

    private fun circularProgressDrawable(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 100f
        circularProgressDrawable.setColorSchemeColors(R.color.blue)
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    private fun getAdapter(): HistoryAdapter {
        val adapter = HistoryAdapter(circularProgressDrawable())
        adapter.onItemClick = { item ->
            navTo(ResultActivity::class.java, item)
        }

        return adapter
    }
}