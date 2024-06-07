package com.lutfisobri.soca.ui.dashboard

import com.lutfisobri.soca.R
import com.lutfisobri.soca.data.models.ItemMenuModel
import com.lutfisobri.soca.databinding.ActivityDashboardBinding
import com.lutfisobri.soca.ui.BaseActivity
import com.lutfisobri.soca.ui.canvas.CanvasActivity
import com.lutfisobri.soca.ui.favorite.FavoriteActivity
import com.lutfisobri.soca.ui.history.HistoryActivity
import com.lutfisobri.soca.ui.login.LoginActivity

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {
    private lateinit var itemMenus: List<ItemMenuModel>

    override fun init() {
        itemMenus = listOf(
            ItemMenuModel(
                icon = R.drawable.camera,
                title = getString(R.string.camera),
                action = { onCameraClick() }
            ),
            ItemMenuModel(
                icon = R.drawable.image,
                title = getString(R.string.gallery),
                action = { onGalleryClick() }
            ),
            ItemMenuModel(
                icon = R.drawable.pencil,
                title = getString(R.string.canvas),
                action = { onCanvasClick() }
            ),
            ItemMenuModel(
                icon = R.drawable.history,
                title = getString(R.string.history),
                action = { onHistoryClick() }
            ),
            ItemMenuModel(
                icon = R.drawable.star,
                title = getString(R.string.favorite),
                action = { onFavoriteClick() }
            )
        )

        with(binding) {
            rvMenu.adapter = ItemMenuAdapter(itemMenus)
            tvName.text = getString(R.string.example_name)
            toolbar.menu.findItem(R.id.action_logout).setOnMenuItemClickListener {
                // TODO: show dialog confirmation
                true
            }
        }
    }

    private fun onCameraClick() {}

    private fun onGalleryClick() {}

    private fun onCanvasClick() {
        navTo(CanvasActivity::class.java)
    }

    private fun onHistoryClick() {
        navTo(HistoryActivity::class.java)
    }

    private fun onFavoriteClick() {
        navTo(FavoriteActivity::class.java)
    }
}