
package com.example.sample01;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sample01.ConfirmDialogFragment.OnConfirmDialogClickListener;

public class HogeFragment extends Fragment implements OnClickListener, OnConfirmDialogClickListener {

    @SuppressWarnings("unused")
    public static final String TAG = "HogeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hoge, container, false);
        Button button = (Button) view.findViewById(R.id.open_confirm_button);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        // this.getFragmentManager() returned null;
        // so get from actibity
        FragmentManager manager = this.getActivity().getSupportFragmentManager();
        String fragmentName = ConfirmDialogFragment.class.getCanonicalName();
        Bundle bundle = new Bundle();
        bundle.putString("parent", TAG); // 戻り先を登録してやる
        Context context = this.getActivity();
        DialogFragment dialogFragment = (DialogFragment) Fragment.instantiate(context,
                fragmentName, bundle);
        dialogFragment.show(manager, ConfirmDialogFragment.TAG);

    }

    @Override
    public void onClickPositive() {
        String a = this.getString(R.string.action_settings);
        Toast.makeText(getActivity(), "そうだよね", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickNegative() {
        Toast.makeText(getActivity(), "いやだよね", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClickNeutral() {
        Toast.makeText(getActivity(), "どうでもいいね!", Toast.LENGTH_SHORT).show();

    }
}
