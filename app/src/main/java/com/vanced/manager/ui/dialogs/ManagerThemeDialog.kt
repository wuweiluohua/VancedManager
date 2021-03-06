package com.vanced.manager.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.android.material.radiobutton.MaterialRadioButton
import com.vanced.manager.databinding.DialogManagerThemeBinding
import com.vanced.manager.ui.core.BindingBottomSheetDialogFragment
import com.vanced.manager.utils.Extensions.getCheckedButtonTag

class ManagerThemeDialog : BindingBottomSheetDialogFragment<DialogManagerThemeBinding>() {

    companion object {

        fun newInstance(): ManagerThemeDialog = ManagerThemeDialog().apply {
            arguments = Bundle()
        }
    }

    private val prefs by lazy { getDefaultSharedPreferences(requireActivity()) }

    override fun binding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DialogManagerThemeBinding.inflate(inflater, container, false)

    override fun otherSetups() {
        bindData()
    }

    private fun bindData() {
        with(binding) {
            val theme = prefs.getString("manager_theme", "System Default")
            root.findViewWithTag<MaterialRadioButton>(theme).isChecked = true
            themeSave.setOnClickListener {
                val newPref = themeRadiogroup.getCheckedButtonTag()
                if (theme != newPref) {
                    prefs.edit { putString("manager_theme", newPref) }
                    dismiss()
                    requireActivity().recreate()
                } else {
                    dismiss()
                }
            }
        }
    }
}