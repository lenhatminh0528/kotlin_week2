package utils

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast

class DialogUtils {
    /**
     * @param context
     * @param title
     * @param message
     * @param positiveText
     * @param negativeText
     * @param positiveListener
     * @param negativeListener
     */
    fun showMessage(
        context: Context, title: String, message: String){
        var builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("OK") {dialog, which ->
                Log.d("TAG","dialog: "+dialog+", _ = "+which+"")

                Toast.makeText(context,"showmeesage thanh cong",Toast.LENGTH_LONG).show()
            }
        }
        builder.show()
    }
}