package com.example.diaaelsayed.ascpoject_mansoura_university_guide;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    GoogleMap Map;

    Polygon polygon ;

    // flag for Internet connection status
    Boolean isInternetPresent = false ;

    // Connection detector class
    ConnectionDetector cd;

    LatLng faculty_of_computer_information;
    LatLng faculty_of_law;
    LatLng faculty_of_Pharmacy;
    LatLng faculty_of_sciences;
    LatLng faculty_of_Commerce;
    LatLng faculty_of_engineering;
    LatLng faculty_of_Education;
    LatLng faculty_of_Agriculture;
    LatLng faculty_of_medicine;
    LatLng mansoura_uni ;

     AlertDialog  alertDialog ;

    Button change;

    Button draw;

    ArrayList<LatLng> markerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cd = new ConnectionDetector(getApplicationContext());

        change = (Button) findViewById(R.id.change);

        draw = (Button) findViewById(R.id.btn_draw);

        registerForContextMenu(change);

        Map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        Map.setMyLocationEnabled(true);
        Map.getUiSettings().setZoomControlsEnabled(true);


       set_static_postion();

     //  find_zoom_my_location();

        drowuniversity_polygon () ;



        draw_lines();

    }

    public void set_static_postion() {

        faculty_of_computer_information = new LatLng(31.042472 , 31.351776);

        MarkerOptions computer_information = new MarkerOptions();


        computer_information.title(getResources().getString(R.string.faculty_of_computer_information));

        computer_information.position(faculty_of_computer_information);

        Map.addMarker(computer_information);

        // ----------------------------------------------------------

        faculty_of_law = new LatLng(31.044223, 31.355102);

        MarkerOptions faculty_of_law = new MarkerOptions();

        faculty_of_law.title(getResources().getString(R.string.faculty_of_law));

        faculty_of_law.position(this.faculty_of_law);

        Map.addMarker(faculty_of_law);

        // ----------------------------------------------------------

        faculty_of_Pharmacy = new LatLng(31.040665, 31.360466);

        MarkerOptions faculty_of_Pharmacy = new MarkerOptions();

        faculty_of_Pharmacy.title(getResources().getString(R.string.faculty_of_Pharmacy));

        faculty_of_Pharmacy.position(this.faculty_of_Pharmacy);

        Map.addMarker(faculty_of_Pharmacy);

        // ----------------------------------------------------------



        faculty_of_sciences = new LatLng(31.041830, 31.352802);

        MarkerOptions faculty_of_sciences = new MarkerOptions();

        faculty_of_sciences.title(getResources().getString(R.string.faculty_of_sciences));

        faculty_of_sciences.position(this.faculty_of_sciences);

        Map.addMarker(faculty_of_sciences);

        // ----------------------------------------------------------

        faculty_of_Commerce = new LatLng(31.042899, 31.355295);

        MarkerOptions faculty_of_Commerce = new MarkerOptions();

        faculty_of_Commerce.title(getResources().getString(R.string.faculty_of_Commerce));

        faculty_of_Commerce.position(this.faculty_of_Commerce);

        Map.addMarker(faculty_of_Commerce);

        // ----------------------------------------------------------

        faculty_of_engineering = new LatLng(31.042807, 31.357999);

        MarkerOptions faculty_of_engineering = new MarkerOptions();

        faculty_of_engineering.title(getResources().getString(R.string.faculty_of_engineering));

        faculty_of_engineering.position(this.faculty_of_engineering);

        Map.addMarker(faculty_of_engineering);

        // ----------------------------------------------------------

        faculty_of_Agriculture = new LatLng(31.041596, 31.356454);

        MarkerOptions faculty_of_Agriculture = new MarkerOptions();

        faculty_of_Agriculture.title(getResources().getString(R.string.faculty_of_Agriculture));

        faculty_of_Agriculture.position(this.faculty_of_Agriculture);

        Map.addMarker(faculty_of_Agriculture);

     //   ----------------------------------------------------------

        faculty_of_Education = new LatLng(31.040828, 31.362494);

        MarkerOptions faculty_of_Education = new MarkerOptions();

        faculty_of_Education.title(getResources().getString(R.string.faculty_of_Education));

        faculty_of_Education.position(this.faculty_of_Education);

        Map.addMarker(faculty_of_Education);

        // ----------------------------------------------------------

        faculty_of_medicine = new LatLng(31.044721, 31.361099);

        MarkerOptions faculty_of_medicine = new MarkerOptions();

        faculty_of_medicine.title(getResources().getString(R.string.faculty_of_medicine));

        faculty_of_medicine.position(this.faculty_of_medicine);



        Map.addMarker(faculty_of_medicine);

        // ----------------------------------------------------------

        mansoura_uni = new LatLng(31.040801, 31.358701);

        MarkerOptions mansoura_uni = new MarkerOptions();

        mansoura_uni.title(getResources().getString(R.string.faculty_of_medicine));

        mansoura_uni.position(this.mansoura_uni);

        Map.addMarker(mansoura_uni);


    }

    public void find_zoom_my_location() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location
                            .getLongitude())) // Sets the center of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(getResources().getString(R.string.menu_header));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_computer_information));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_law));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_Pharmacy));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_sciences));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_Commerce));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_engineering));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_Education));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_Agriculture));
        menu.add(0, v.getId(), 0, getResources().getString(R.string.faculty_of_medicine));






    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == getResources().getString(R.string.faculty_of_computer_information)) {

            find_zoom_faculty_of_computer_information();

        } else if (item.getTitle() == getResources().getString(R.string.faculty_of_law)) {

            find_zoom_faculty_of_law();

        } else if (item.getTitle() ==  getResources().getString(R.string.current)) {

            find_zoom_my_location();

        } else if (item.getTitle() == getResources().getString(R.string.faculty_of_Pharmacy)) {

            find_zoom_faculty_of_Pharmacy();
        }

        else if (item.getTitle() == getResources().getString(R.string.faculty_of_sciences)) {

            find_zoom_faculty_of_sciences();

        }
        else if (item.getTitle() == getResources().getString(R.string.faculty_of_Commerce)) {

            find_zoom_faculty_of_Commerce();

        }
        else if (item.getTitle() == getResources().getString(R.string.faculty_of_engineering)) {

            find_zoom_faculty_of_engineering();

        }
        else if (item.getTitle() ==getResources().getString(R.string.faculty_of_Education)) {

            find_zoom_faculty_of_Education();

        }

        else if (item.getTitle() == getResources().getString(R.string.faculty_of_Agriculture)) {

            find_zoom_faculty_of_Agriculture();

        }

        else if (item.getTitle() == getResources().getString(R.string.faculty_of_medicine)) {

            find_zoom_faculty_of_medicine();

        }
        else {
            return false;
        }
        return true;
    }

    public void find_zoom_faculty_of_computer_information() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();


        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_computer_information) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    public void find_zoom_faculty_of_law() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_law) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    public void find_zoom_faculty_of_Pharmacy() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_Pharmacy) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    public void find_zoom_faculty_of_sciences() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_sciences) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    public void find_zoom_faculty_of_Commerce() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_Commerce) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    public void find_zoom_faculty_of_engineering() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_engineering) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    public void find_zoom_faculty_of_Education() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_Education) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    public void find_zoom_faculty_of_Agriculture() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_Agriculture) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }

    public void find_zoom_faculty_of_medicine() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(faculty_of_medicine) // Sets the center
                            // of the map to
                            // location user
                    .zoom(18) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }

    }


    public void drowuniversity_polygon (){

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager
                .getLastKnownLocation(locationManager.getBestProvider(criteria,
                        false));
        if (location != null) {
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                    location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(mansoura_uni) // Sets the center
                            // of the map to
                            // location user
                    .zoom(15) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(40) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            Map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

        }



         polygon = Map.addPolygon(new PolygonOptions()
                .add(new LatLng(31.044683, 31.365308),
                        new LatLng(31.045510, 31.357572),
                        new LatLng(31.045482, 31.353892),
                        new LatLng(31.045023, 31.352701),
                        new LatLng(31.045271, 31.350727),
                        new LatLng(31.045188, 31.349461),
                        new LatLng(31.040788, 31.350215),
                        new LatLng(31.040889, 31.351889),
                        new LatLng(31.036007, 31.358476),
                        new LatLng(31.036007, 31.358476),
                        new LatLng(31.037874, 31.361298),
                        new LatLng(31.040006, 31.363604),
                        new LatLng(31.044731, 31.365364))
                .strokeColor(Color.BLUE)
                .fillColor(0x330000ff))

                ;


    }




    @Override
    public void onClick(View v) {



    }

    public void draw_lines() {

        // Initializing
        markerPoints = new ArrayList<LatLng>();

        Map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                // Already 10 locations with 8 waypoints and 1 start location
                // and 1 end location.
                // Upto 8 waypoints are allowed in a query for non-business
                // users
                if (markerPoints.size() >= 10) {
                    return;
                }

                // Adding new item to the ArrayList
                markerPoints.add(point);

                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();

                // Setting the position of the marker
                options.position(point);

                /**
                 * For the start location, the color of marker is GREEN and for
                 * the end location, the color of marker is RED and for the rest
                 * of markers, the color is AZURE
                 */
                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_RED));
                } else {
                    options.icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                }

                // Add new marker to the Google Map Android API V2
                Map.addMarker(options);
            }
        });

        // The map will be cleared on long click
        Map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {
                // Removes all the points from Google Map
                Map.clear();

                // Removes all the points in the ArrayList
                markerPoints.clear();
            }
        });

        // Click event handler for Button btn_draw
        draw.setOnClickListener(new View.OnClickListener() {




                @Override
            public void onClick(View v) {

                isInternetPresent = cd.isConnectingToInternet();

                // check for Internet status

                    if (isInternetPresent) {

                    // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                } }

                    else {

                        Toast.makeText(MainActivity.this ,getResources().getString(R.string.connection ), Toast.LENGTH_LONG).show();

                    }



            }
        });

    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + ","
                + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Waypoints
        String waypoints = "";
        for (int i = 2; i < markerPoints.size(); i++) {
            LatLng point = (LatLng) markerPoints.get(i);
            if (i == 2)
                waypoints = "waypoints=";
            waypoints += point.latitude + "," + point.longitude + "|";
        }

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&"
                + waypoints;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + parameters;

        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {


        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service

            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {

            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            Map.addPolyline(lineOptions);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);

      return true;


    }

    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.compass:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                Intent i = new Intent(MainActivity.this , compass.class);

                startActivity(i);

                return true;

            case R.id.how_to_use:

                 alertDialog = new AlertDialog.Builder(
                        MainActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle(getResources().getString(R.string.how_to_use));

                // Setting Dialog Message

                alertDialog.setMessage(getResources().getString(R.string.how_to_use_message));

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.how_to_use);

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       alertDialog.dismiss();
                    }
                });

                // Showing Alert Message
                alertDialog.show();



                return true;

            case R.id.rmove_shape:

                polygon.remove();

                return true;

            case R.id.add_shape:

               drowuniversity_polygon();

                return true;

            case R.id.abouts:

                alertDialog = new AlertDialog.Builder(
                        MainActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle(getResources().getString(R.string.about));

                // Setting Dialog Message
                alertDialog.setMessage(getResources().getString(R.string.about_message));

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.about);

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                // Showing Alert Message
                alertDialog.show();



                return true;


            case R.id.exit:

                finish();

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
