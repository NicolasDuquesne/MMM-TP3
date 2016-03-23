package projet.istic.fr.tp3;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements RegionList.OnRegionSelectedListener {

    boolean detailPage = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("AndroidFragmentActivity", "onCreate()");
        Log.v("MainsavedInstanceState", savedInstanceState == null ? "true" : "false");

        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            RegionList regionList = new RegionList();
            ft.add(R.id.regionList, regionList, "RegionList");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }

        if(findViewById(R.id.regionDetail) != null){
            detailPage = true;
            getFragmentManager().popBackStack();

            RegionDetail regionDetail = (RegionDetail) getFragmentManager().findFragmentById(R.id.regionDetail);
            if(regionDetail == null){
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                regionDetail = new RegionDetail();
                ft.replace(R.id.regionDetail, regionDetail, "RegionDetail");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }

    }


    @Override
    public void onRegionSelected(String Region) {
        Log.v("MainActivity",Region);

        if(detailPage){
            RegionDetail detailFragment = (RegionDetail)
                    getFragmentManager().findFragmentById(R.id.regionDetail);
            detailFragment.updateRegionContent(Region);
        }
        else{
            RegionDetail detailFragment = new RegionDetail();
            detailFragment.setRegionContent(Region);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.regionList, detailFragment, "Detail_Fragment2");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}
