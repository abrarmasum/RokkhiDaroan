package rokkhi.abrar.rokkhidaroan;

import android.app.Application;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.nostra13.universalimageloader.core.ImageLoader;

import rokkhi.abrar.rokkhidaroan.utils.UniversalImageLoader;

public class RokkhiDaroan extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());

//        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                .setTimestampsInSnapshotsEnabled(true)
//                .build();
//        firestore.setFirestoreSettings(settings);

    }
}
