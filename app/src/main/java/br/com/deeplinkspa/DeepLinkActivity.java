package br.com.deeplinkspa;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.deeplinkspa.databinding.ActivityDeepLinkBinding;

public class DeepLinkActivity extends AppCompatActivity {

    private ActivityDeepLinkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_deep_link);
        binding.buttonGoto.setOnClickListener(this::onClick);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri data = intent.getData();
        String link = data.toString();
        Fragment base = LinkManager.discover(link);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_deep_link_content, base, link);
        fragmentTransaction.commit();
    }

    private void onClick(View view) {
        LinkManager.goTo(this, "/first");
    }
}
