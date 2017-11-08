package br.com.deeplinkspa;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.deeplinkspa.databinding.FragmentFirstBinding;
import br.com.deeplinkspa.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_second, null, false);
        bind.textLink.setText("second");
        bind.buttonGoto.setOnClickListener(this::onClick);
        return bind.getRoot();
    }

    private void onClick(View view) {
        LinkManager.goTo(getActivity(), "/first");
    }

}
