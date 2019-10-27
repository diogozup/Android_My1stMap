package com.pandapanda.cmu_gps;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


//----------------------------------------------------------------------- onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

//----------------------------------------------------------------------- Map Methods

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Mudar exibicao do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        final LatLng ESTG = new LatLng(41.367107, -8.194673);
        //41.367107, -8.194673 <<-- Coordenadas de ESTG



        //---------------------------------------------------- Desenhar Circulo


        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center( ESTG );
        circleOptions.radius(500); //distancia em metros
        circleOptions.strokeWidth(10); //borda
        circleOptions.strokeColor(Color.BLUE);
        circleOptions.fillColor(Color.argb(128, 220, 165, 183)); //alph 0 ate 255 Opacidade

        mMap.addCircle(circleOptions);

        //---------------------------------------------------- Desenhar Poligono
        final PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add (new LatLng(41.367107, -8.194673));
        polygonOptions.add (new LatLng(41.337327, -8.196625));
        polygonOptions.add (new LatLng(41.316147, -8.198652));
        polygonOptions.add (new LatLng(41.328147, -8.199752));
        //colors
        polygonOptions.strokeWidth(10); //borda
        polygonOptions.strokeColor(Color.BLUE);
        polygonOptions.fillColor(Color.argb(128, 179, 198, 255)); //alph 0 ate 255 Opacidade

        mMap.addPolygon(polygonOptions);




        // Adicionado evento de clique no mapa
        // 1- click rapido
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "onClick-> Lat: " + latitude + " Long: " + longitude,
                        Toast.LENGTH_SHORT).show();



                mMap.addMarker(
                        new MarkerOptions()
                            .position(latLng)
                            .title("Local")
                            .snippet("Descricao")
                            .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.icone_loja))
                );
            }
        });

        // 2- click longo
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                /*
                Toast.makeText(MapsActivity.this,
                        "onLongClick-> Lat: " +
                        latitude + " Long: " + longitude,
                        Toast.LENGTH_SHORT).show();
                */

                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.add(ESTG);
                polylineOptions.add(latLng);
                polylineOptions.color(Color.RED);
                polylineOptions.width(20);

                mMap.addPolyline(polylineOptions);


                mMap.addMarker(
                        new MarkerOptions()
                        .position( latLng)
                        .title("Local")
                        .snippet("Descricao")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_carro_roxo_48px))
                );
            }
        });





        mMap.addMarker(new MarkerOptions()
                .position(ESTG)
                .title("ESTG - Felgueiras")
                /*.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory
                                .HUE_CYAN))*/
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.panda1))
        );


        mMap.moveCamera(// range do valor "2.0" ate "21.0"
                CameraUpdateFactory.newLatLngZoom(ESTG,15)
        );







    }
}
