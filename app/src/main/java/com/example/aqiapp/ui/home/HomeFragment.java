package com.example.aqiapp.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aqiapp.R;
import com.example.aqiapp.api.object.Aqi;
import com.example.aqiapp.api.object.Device;
import com.example.aqiapp.ui.device.DeviceViewModel;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel model;
    private MapView mMapView;
    private MapController mMapController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mMapView = root.findViewById(R.id.map);
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setBuiltInZoomControls(true);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(9);

        model = new ViewModelProvider(this).get(HomeViewModel.class);
        model.getCurrentAqi().observe(getViewLifecycleOwner(), new Observer<List<Aqi>>() {
            @Override
            public void onChanged(List<Aqi> aqis) {
                for(Aqi aqi:aqis) {
                    addMaker(aqi.getLat(), aqi.getLon());
                }
            }
        });

        moveToPosition(21.0005, 105.002);
        addMaker(21.0005, 105.002);
        return root;
    }

    public void addMaker(double lat, double lon) {
        GeoPoint startPoint = new GeoPoint(lat, lon);
        Marker startMarker = new Marker(mMapView);
        startMarker.setPosition(startPoint);
        startMarker.setIcon(writeOnDrawable(R.drawable.green, "30"));
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapView.getOverlays().add(startMarker);
    }

    public void moveToPosition(double lat, double lon) {
        GeoPoint currentGeo = new GeoPoint(lat, lon);
        mMapView.getController().animateTo(currentGeo);
    }

    public BitmapDrawable writeOnDrawable(int drawableId, String text){
        Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);
        bm = Bitmap.createScaledBitmap(bm, 80, 80, true);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(25);
        paint.setTextAlign(Paint.Align.CENTER);
        Canvas canvas = new Canvas(bm);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;
        canvas.drawText(text, xPos, yPos, paint);
        return new BitmapDrawable(getResources(), bm);
    }
}
