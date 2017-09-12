package fm.weigl.refsberlin.update.view

import android.app.Activity
import android.support.v7.app.AlertDialog
import fm.weigl.refdata.appversion.AppVersion
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.databinding.UpdateDialogBinding
import javax.inject.Inject

interface IUpdateDialogView {
    fun showUpdateDialog(appVersion: AppVersion)
}

interface UpdateDialogDelegate {
    fun updateClicked()
}

class UpdateDialogView @Inject constructor(
        private val activity: Activity
) : IUpdateDialogView {

    var delegate: UpdateDialogDelegate? = null

    override fun showUpdateDialog(appVersion: AppVersion) {

        val builder = AlertDialog.Builder(activity)
        val binding = UpdateDialogBinding.inflate(activity.layoutInflater)
        binding.appVersion = appVersion
        builder.setView(binding.root)
        builder.setPositiveButton(R.string.update_dialog_button_update, { dialog, _ ->

            dialog.dismiss()
            delegate?.updateClicked()

        })
        builder.setNegativeButton(R.string.update_dialog_button_no_thanks, { dialog, _ ->

            dialog.dismiss()

        })
        builder.show()

    }
}