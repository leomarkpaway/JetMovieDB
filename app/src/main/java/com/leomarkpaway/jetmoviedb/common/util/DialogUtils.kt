package com.leomarkpaway.jetmoviedb.common.util

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context


fun loadingDialog(context: Context, message: String): ProgressDialog {
    val dialog = ProgressDialog(context)
    dialog.setMessage(message)
    dialog.setCancelable(false)
    return dialog
}

fun showLoadingDialog(context: Context, message: String): ProgressDialog {
    val dialog = loadingDialog(context, message)
    dialog.show()
    return dialog
}

fun loadingDialog(context: Context): ProgressDialog {
    return loadingDialog(context, "Loading...")
}

fun showLoadingDialog(context: Context): ProgressDialog {
    val dialog = loadingDialog(context)
    dialog.show()
    return dialog
}

fun showLoadingDialog(context: Context, dialog: ProgressDialog?): ProgressDialog {
    var dialogTemp = dialog
    if (dialogTemp == null) {
        dialogTemp = loadingDialog(context)
    }
    if (!dialogTemp.isShowing) {
        dialogTemp.show()
    }
    return dialogTemp
}

fun tryDismissDialog(dialog: Dialog?) {
    dialog?.dismiss()
}
