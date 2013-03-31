
package com.example.sample01;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class ConfirmDialogFragment extends DialogFragment implements
        DialogInterface.OnClickListener {

    @SuppressWarnings("unused")
    public static final String TAG = "ConfirmDialogFragment";

    /**
     * リスナーインスタンス
     */
    OnConfirmDialogClickListener mListener;

    /**
     * ボタンクリックた時のリスナー
     */
    public interface OnConfirmDialogClickListener {

        public void onClickPositive(/* some arguments */);

        public void onClickNegative();

        public void onClickNeutral();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = this.getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.title_confirm_dialog)
                .setPositiveButton(R.string.label_positive_button, this)
                .setNegativeButton(R.string.label_negative_button, this)
                .setNeutralButton(R.string.label_neutral_button, this);

        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);

        Bundle arguments = this.getArguments();
        String parent = arguments.getString("parent");
        if (parent != null) {
            FragmentManager manager = this.getActivity().getSupportFragmentManager();
            Fragment parentFragment = manager.findFragmentByTag(parent);
            if (parentFragment != null && parentFragment instanceof OnConfirmDialogClickListener) {
                this.mListener = (OnConfirmDialogClickListener) parentFragment;
            }
        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (this.mListener != null) {
            switch (which) {
                case Dialog.BUTTON_POSITIVE:
                    this.mListener.onClickPositive();
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    this.mListener.onClickNegative();
                    break;
                case Dialog.BUTTON_NEUTRAL:
                    this.mListener.onClickNeutral();
                    break;
                default:
                    break;
            }
        }
    }

}
