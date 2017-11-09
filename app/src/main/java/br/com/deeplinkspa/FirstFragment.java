package br.com.deeplinkspa;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.deeplinkspa.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_first, null, false);
        bind.textLink.setText("first");
        bind.buttonGoto.setOnClickListener(this::onClick);
        return bind.getRoot();
    }

    private void onClick(View view) {
        LinkManager.me().open("/second");
    }

}
