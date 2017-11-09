package br.com.deeplinkspa;


public class LinkManager {

    private static LinkManager linkManager = new LinkManager();
    private LinkManagerCallback callback;

    public static LinkManager me(){
        return linkManager;
    }

    public void open(String link) {
        callback.open(link);
    }

    public void setLinkManagerCallback(LinkManagerCallback callback){
        this.callback = callback;
    }


}
