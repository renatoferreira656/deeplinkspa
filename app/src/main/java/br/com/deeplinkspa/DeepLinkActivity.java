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

    private static final String BASE_URL = "deep://local";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_deep_link);
        binding.buttonGoto.setOnClickListener(this::onClick);
        LinkManager.me().setLinkManagerCallback(this::openFragment);
    }

    private void openFragment(String link) {
        String fullLink =  BASE_URL + link;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(fullLink));
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri data = intent.getData();
        String link = data.toString();
        Fragment base = discover(link);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_deep_link_content, base, link);
        fragmentTransaction.commit();
    }

    public static Fragment discover(String link) {
        try {
            String replace = link.replace(BASE_URL, "");
            replace = replace.replace("/","");
            String c = replace.charAt(0) + "";
            c = c.toUpperCase();
            char[] chars = replace.toCharArray();
            chars[0] = c.toCharArray()[0];
            String clazzFirstName = new String(chars) + "Fragment";
            String clazz = DeepLinkActivity.class.getPackage().getName() + "." + clazzFirstName;
            Class<?> aClass = Class.forName(clazz);
            return (Fragment) aClass.newInstance();
        } catch(Exception e){
            throw new RuntimeException(e);
        }

    }

    private void onClick(View view) {
        LinkManager.me().open("/first");
    }
}
