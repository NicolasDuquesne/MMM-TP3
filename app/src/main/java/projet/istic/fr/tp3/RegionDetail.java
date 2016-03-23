package projet.istic.fr.tp3;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

public class RegionDetail extends Fragment  {

    String myRegion = "";

    Map<String, String> urlByRegion = new HashMap<String, String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("RegionDetail", "onCreate()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //TODO : map correspondance région et url de technoresto
        urlByRegion.put("Alsace", "http://technoresto.org/vdf/alsace/index.shtml");
        urlByRegion.put("Beaujolais", "http://technoresto.org/vdf/beaujolais/index.html");
        urlByRegion.put("Jura", "http://technoresto.org/vdf/jura/index.html");
        urlByRegion.put("Champagne", "http://technoresto.org/vdf/champagne/index.html");
        urlByRegion.put("Savoie", "http://technoresto.org/vdf/savoie/index.html");
        urlByRegion.put("Languedoc-Roussillon", "http://technoresto.org/vdf/languedoc/index.html");
        urlByRegion.put("Bordelais", "http://technoresto.org/vdf/bordelais/index.html");
        urlByRegion.put("Vallée du Rhone", "http://technoresto.org/vdf/cotes_du_rhone/index.html");
        urlByRegion.put("Provence", "http://technoresto.org/vdf/provence/index.html");
        urlByRegion.put("Val de Loire", "http://technoresto.org/vdf/val_de_loire/index.html");
        urlByRegion.put("Sud-Ouest", "http://technoresto.org/vdf/sud-ouest/index.html");
        urlByRegion.put("Corse", "http://technoresto.org/vdf/corse/index.html");
        urlByRegion.put("Bourgogne", "http://technoresto.org/vdf/bourgogne/index.html");

        super.onActivityCreated(savedInstanceState);
        Log.v("RegionDetail", "onActivityCreated()");
        if (savedInstanceState != null) {
            myRegion = savedInstanceState.getString("currentRegion", "");
        }
        if(!myRegion.trim().equalsIgnoreCase("")){
            String wineURL = urlByRegion.get(myRegion);
            WebView myWebView = (WebView) getView().findViewById(R.id.pageInfo);
            myWebView.getSettings().setJavaScriptEnabled(true);
            myWebView.setWebViewClient(new MyWebViewClient());
            myWebView.loadUrl(wineURL.trim());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentRegion", myRegion);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("RegionDetail", "onCreateView()");
        View view = inflater.inflate(R.layout.region_detail, container, false);
        return view;
    }

    public void setRegionContent(String Region) {
        myRegion = Region;
    }

    public void updateRegionContent(String Region) {
        myRegion = Region;
        String wineURL = urlByRegion.get(myRegion);
        WebView myWebView = (WebView) getView().findViewById(R.id.pageInfo);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(wineURL.trim());
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
