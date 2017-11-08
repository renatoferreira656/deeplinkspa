package br.com.deeplinkspa;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class LinkManager {
    private static final String BASE_URL = "deep://local";

    public static void goTo(Context context, String link) {
        String fullLink =  BASE_URL + link;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(fullLink));
        context.startActivity(intent);
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
}
