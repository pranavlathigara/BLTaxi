package com.vlad1m1r.actions.executors

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.util.Patterns
import com.vlad1m1r.actions.R
import com.vlad1m1r.bltaxi.domain.Action

class CallNumberExecutor(private val context: Context): Executor {
    override fun canHandleAction(action: Action): Boolean {
        return action is Action.CallNumberAction
    }

    override operator fun invoke(action: Action): Intent {
        val action = action as Action.CallNumberAction
        if (Patterns.PHONE.matcher(action.phoneNumber).matches()) {
            val i = Intent(Intent.ACTION_DIAL)
            i.data = Uri.parse("tel:${action.phoneNumber}")
            val chooser = Intent.createChooser(i, context.resources.getString(R.string.action__call_number))
            chooser.addFlags(FLAG_ACTIVITY_NEW_TASK)
            return chooser
        } else {
            throw IllegalArgumentException("Phone number ${action.phoneNumber} has a wrong format")
        }
    }
}
