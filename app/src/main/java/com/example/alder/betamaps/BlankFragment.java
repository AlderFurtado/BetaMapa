package com.example.alder.betamaps;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;

public class BlankFragment extends SupportMapFragment implements OnMapReadyCallback,GoogleMap.OnMapClickListener,
        GoogleMap.OnPoiClickListener,GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private Marker marker = null;

    private boolean mapaMarcado = false;
    private LocationManager locationManager ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getMapAsync(this);
        Toast.makeText(getContext(),"Pressione uma local para criar um evento",Toast.LENGTH_LONG).show();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



    public void adicioanarMarcador(double lat, double lng){
        marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat,lng))
                .title("teste")
                .snippet("Population: teste\nteste\nteste\nteste\nteste"));

        Toast.makeText(getContext(),lat+"\n"+lng,Toast.LENGTH_LONG ).show();
        Log.d("Localização","latitude :"+lat +"\n Longitude:"+lng);
        mapaMarcado = true;
    }


    public void removerMarcador(Marker marker){
        marker.remove();
    }

    public void inicilizarMapaMarcadores(GoogleMap mMap,double lat,double lng,String titulo){
        LatLng eventos = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(eventos).title(titulo));
        Log.d("Inicializador","mapa iniciado com 3 marcados");
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {


        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria,true);

            Toast.makeText(getContext(),provider,Toast.LENGTH_SHORT).show();

            mMap = googleMap;

            mMap.setOnMapClickListener(this);

            mMap.setOnMapLongClickListener(this);

            mMap.setOnPoiClickListener(this);

            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setMyLocationEnabled(true);

        }catch (SecurityException e){

        }


        /*LatLng itCenter = new LatLng(-1.4164848299996655, -48.47942691296339);
        LatLng arredores1 = new LatLng(-1.4369263113558024, -48.493066281080246);
        LatLng arredores2 = new LatLng(-1.4369263113558024, -48.47732372581959);

        /*MarkerOptions markerOptions = new MarkerOptions() ;
        markerOptions.position(itCenter);
        markerOptions.title("Marker in Sidney");
        mMap.addMarker(markerOptions);

        mMap.addMarker(new MarkerOptions().position(arredores1).title("teste1"));
        mMap.addMarker(new MarkerOptions().position(itCenter).title("itCenter"));
        mMap.addMarker(new MarkerOptions().position(arredores2).title("teste2"));*/

        double[] latitudes = new double[3];
        double[] longitudes = new double[3];
        latitudes[0] = -1.4164848299996655;
        latitudes[1] = -1.4369263113558024;
        latitudes[2] = -1.4369263113558024;
        longitudes[0] = -48.47942691296339;
        longitudes[1] = -48.493066281080246;
        longitudes[2] = -48.47732372581959;

        String[] titulos = new String[3];
        titulos[0] = "titulo1";
        titulos[1] = "titulo2";
        titulos[2] = "titulo3";

        for (int i = 0;i<3;i++){
            inicilizarMapaMarcadores(mMap,latitudes[i],longitudes[i],titulos[i]);
        }

    }

    @Override
    public void onMapClick(LatLng latLng) {


    }

    @Override
    public void onPoiClick(PointOfInterest poi) {
        Toast.makeText(getContext(), "Clicou em: " +
                        poi.name +
                        "\nLatitude:" + poi.latLng.latitude +
                        " Longitude:" + poi.latLng.longitude,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if(!mapaMarcado){
            adicioanarMarcador(latLng.latitude,latLng.longitude);
        }else{
            removerMarcador(marker);
            adicioanarMarcador(latLng.latitude,latLng.longitude);
        }
    }
}
