package com.corner.searcharchitect.ui.info

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.corner.searcharchitect.R
import com.corner.searcharchitect.databinding.InfoDialogFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoDialogFragment : DialogFragment() {

    private lateinit var binding: InfoDialogFragmentBinding

    private lateinit var viewModel: InfoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel.dismissDialog = ::dismiss
        viewModel.sendEmail = ::sendEmail

        return activity?.let { fragmentActivity ->
            val builder = AlertDialog.Builder(fragmentActivity)
            val inflater = fragmentActivity.layoutInflater

            binding = DataBindingUtil.inflate(inflater, R.layout.info_dialog_fragment, null, false)
            binding.lifecycleOwner = this
            binding.vm = viewModel

            builder.setView(binding.root)
                .setPositiveButton(getString(R.string.ok)) { _, _ ->
                    dismiss()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun sendEmail(email: String) {
        val uri = Uri.parse("mailto:$email")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        startActivity(Intent.createChooser(intent, null))
    }

}