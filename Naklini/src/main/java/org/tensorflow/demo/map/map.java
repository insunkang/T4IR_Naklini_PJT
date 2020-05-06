package org.tensorflow.demo.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.tensorflow.demo.R;
import org.tensorflow.demo.map.db.DataAdapter;
import org.tensorflow.demo.map.db.Freshwater;
import org.tensorflow.demo.map.db.Onboard;
import org.tensorflow.demo.map.db.Rock;
import org.tensorflow.demo.map.db.Sea;
import org.tensorflow.demo.mappoint.DBHandler;
import org.tensorflow.demo.mappoint.FreshWater;
import org.tensorflow.demo.mappoint.OnBoard;
import org.tensorflow.demo.mappoint.Point1;
import org.tensorflow.demo.pointdetail.Point_Info_Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class map extends AppCompatActivity implements OnMapReadyCallback {
    // 인텐트에 보낼 리퀘스트 코드(100+i)
    public static final int CALL_POINT_CODE = 1001;
    String[] permission_list={
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    GoogleMap map;
    LocationManager locationManager;
    Geocoder geocoder;
    List<Onboard> onboardList;
    List<Freshwater> freshwaterList;
    List<Rock> rockList;
    List<Sea> seaList;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Spinner spinner;
    List<String> spinnerlist;
    Button listBtn;
    Intent intent;
    Intent detail_intent;
    String returnCategory="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        drawerLayout = findViewById(R.id.main_drawer);
        listBtn = findViewById(R.id.button2);
        //액션바에 버튼 설정 - 버튼을 선택하면 NavigationView가 display
        //                    버튼을 다시 선택하면 NavigationView가 화면에서 사라지도록 설정
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout, R.string.open_str,R.string.close_str);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            requestPermissions(permission_list,1000);
        }else{
            init();
        }
        DBHandler handler = DBHandler.open(this);
        // TODO: 연결- Point ==============================================================================
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPoint1();
            }
        });

        // TODO: 연결- detail =============================================================================
        detail_intent = new Intent(org.tensorflow.demo.map.map.this, Point_Info_Activity.class);

    }
    // 지역으로 보기 버튼으로 포인트 불러온 후 '뒤로가기 버튼' 누르면 다시 리스트뷰 페이지로
    // 분류 보기 옵션으로 불러온 후 '뒤로가기 버튼' 누르면 메인 화면으로
    @Override
    public void onBackPressed() {
        if(!returnCategory.equals("")){
            callPoint1();
        }else{
            super.onBackPressed();
        }
    }
    public void callPoint1(){
        // Point1.java 부르기
        intent = new Intent(org.tensorflow.demo.map.map.this, Point1.class);
        intent.putExtra("code", "callPoint1");
        startActivityForResult(intent, CALL_POINT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        //String returnCategory = "";
        if(requestCode==CALL_POINT_CODE){
            if(resultCode==RESULT_OK){
                // @서로 넘길때 OnBoard(mappoint)객체를 통째로 넘겨주고 onboard와 _id로 연결
                returnCategory = intent.getStringExtra("returnCategory");
                // 카테고리 목록 다 부르고 내가 선택한 좌표, 위치로 zoom
                switch(returnCategory){
                    case "Onboard":
                        //onboard();
                        OnBoard point1 = (OnBoard)intent.getParcelableExtra("Parcelable");
                        LatLng myloc1 = new LatLng(Double.parseDouble(point1.latitude),Double.parseDouble(point1.longitude));
                        pointZoom(point1.point_nm,myloc1);
                        detail_intent.putExtra("categoryDetail", "Onboard");
                        break;
                    case "Freshwater":
                        FreshWater point2 =
                                (FreshWater) intent.getParcelableExtra("Parcelable");
                        // 위도, 경도 말고 위치로 검색
                        pointZoom2(point2.name, point2.addr);
                        detail_intent.putExtra("categoryDetail", "Freshwater");
                        //fresh();
                        break;
                    case "Rock":
                        //rock();
                        org.tensorflow.demo.mappoint.Rock point3 =
                                (org.tensorflow.demo.mappoint.Rock) intent.getParcelableExtra("Parcelable");
                        LatLng myloc3 = new LatLng(Double.parseDouble(point3.latitude),Double.parseDouble(point3.longitude));
                        pointZoom(point3.point_nm, myloc3);
                        detail_intent.putExtra("categoryDetail", "Rock");
                        break;
                    case "Sea":
                        org.tensorflow.demo.mappoint.Sea point4 =
                                (org.tensorflow.demo.mappoint.Sea) intent.getParcelableExtra("Parcelable");
                        // 위도, 경도 말고 위치로 검색
                        pointZoom2(point4.name, point4.addr);
                        detail_intent.putExtra("categoryDetail", "Sea");
                        //sea();
                        break;
                }

            }
        }
    }

    public void pointZoom(String name, LatLng myloc){
        map.clear();
        // 마커 생성
        MarkerOptions mOptions2 = new MarkerOptions();
        mOptions2.title(name);
        mOptions2.snippet("자세히 보기");
        mOptions2.position(myloc);
        // 마커 추가
        map.addMarker(mOptions2);
        // 해당 좌표로 화면 줌
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc, 15));
    }

    public void pointZoom2(String name, String address){
        // 주소 한 개를 넣는다
        String pnt_name = name;
        List<Address> addressList = null;

        try {
            // 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
            addressList = geocoder.getFromLocationName(
                    address, // 주소
                    10); // 최대 검색 결과 개수
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addressList != null) {
            // 콤마를 기준으로 split
            String[] splitStr = addressList.get(0).toString().split(",");
            String address_ = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소

            String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
            String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도

            // 좌표(위도, 경도) 생성
            LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            // 줌
            pointZoom(name, point);
        }else{
            Log.d("result","검색 결과 없음");
        }
    }


    // TODO: =============================================================================================


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_navi,menu);

        spinnerlist = new ArrayList<>();
        spinnerlist.add("낚시 포인트별 전체 조회");
        spinnerlist.add("선상낚시");
        spinnerlist.add("민물낚시");
        spinnerlist.add("갯바위낚시");
        spinnerlist.add("바다낚시");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,spinnerlist);

        spinner = findViewById(R.id.dropdown);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                returnCategory = "";
                if (position == 1){
                    onboard();
                    detail_intent.putExtra("categoryDetail", "Onboard");
                }else if (position == 2){
                    fresh();
                    detail_intent.putExtra("categoryDetail", "Freshwater");
                }else if (position == 3){
                    rock();
                    detail_intent.putExtra("categoryDetail", "Rock");
                }else if (position == 4){
                    sea();
                    detail_intent.putExtra("categoryDetail", "Sea");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        MenuItem search_item = menu.findItem(R.id.Item0);
        SearchView searchView = (SearchView) search_item.getActionView();
        //안내문구를 등록
        searchView.setQueryHint("검색어를 입력하세요");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int result:grantResults){
            if(result== PackageManager.PERMISSION_DENIED){
                return;
            }
        }
        init();
    }

    //맵정보 추출
    public void init(){
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)manager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    //지도가 준비되면 자동으로 호출되는 메소드
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("mymap","준비완료");
        map = googleMap;
        geocoder = new Geocoder(this);
        if (map!=null){
            getMyLocation();
        }
        // TODO: 연결 - detail =======================================================================
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                // Rock, OnBoard: point_nm,
                // Sea, FreshWater: name
                detail_intent.putExtra("POINT_NM", marker.getTitle());
                startActivity(detail_intent);
            }
        });

    }

    public void search(String query){
        String str=query;
        List<Address> addressList = null;
        try {
            // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
            addressList = geocoder.getFromLocationName(
                    str, // 주소
                    10); // 최대 검색 결과 개수
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (addressList != null) {
            Log.d("result", addressList.get(0).toString());
            System.out.println(addressList.get(0).toString());
            // 콤마를 기준으로 split
            String[] splitStr = addressList.get(0).toString().split(",");
            String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
            System.out.println(address);

            String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
            String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
            System.out.println(latitude);
            System.out.println(longitude);

            // 좌표(위도, 경도) 생성
            LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            // 마커 생성
            MarkerOptions mOptions2 = new MarkerOptions();
            mOptions2.title("search result");
            mOptions2.snippet(address +"\n자세히 보기");
            mOptions2.position(point);
            // 마커 추가
            map.addMarker(mOptions2);
            // 해당 좌표로 화면 줌
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
        }else{
            Toast.makeText(this,"검색 결과가 없습니다.",Toast.LENGTH_LONG).show();
            Log.d("result","검색 결과 없음");
        }
    }

    //location을 추출 - 현재 나의 위치정보를 추출
    public void getMyLocation(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            for (String permission:permission_list){
                if (checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED){
                    return;
                }
            }
        }
        //이전에 측정했었던 값을 가져오고 - 새롭게 측정하는데 시간이 많이 걸릴 수 있으므로
        Location gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (gps_loc != null){
            setMyLocation(gps_loc);
        }else {
            if (network_loc != null){
                setMyLocation(network_loc);
            }
        }
        Log.d("myloc","=====================================");
        //현재 측정한 값도 가져오고
        MyLocationListener locationListener = new MyLocationListener();
        //현재 활성화되어 있는 Provider를 체크
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    3000,10,locationListener);
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    3000,10,locationListener);
        }
    }
    //location 정보를 지도에 셋팅하는 메소드
    public void setMyLocation(Location myLocation){
        Log.d("myloc","위도 : "+myLocation.getLatitude());
        Log.d("myloc","경도 : "+myLocation.getLongitude());
        LatLng myloc = new LatLng(myLocation.getLatitude(),myLocation.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(myloc,15);
        map.moveCamera(cameraUpdate);
        //현재 위치를 포인트로 표시
        map.setMyLocationEnabled(true);
    }
    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            //현재 위도 경도가 변경되면 호출되는 메소드
            setMyLocation(location);
            //리스너연결해제
            locationManager.removeUpdates(this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    public void setPosition(){
        LatLng myloc = new LatLng(37.5013395,127.0393345);
        // 카메라가 이동할때 애니메이션이 적용
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(myloc,7));
    }

    public void onboard(){
        map.clear();
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        onboardList = mDbHelper.getTableData();

        // db 닫기
        mDbHelper.close();


        //String latitude = onboardList.get(1).getLatitude(); // 위도
        //String longitude = onboardList.get(1).getLongitude(); // 경도

        setPosition();
        for (int i=1; i<onboardList.size(); i++){
            String latitude2 = onboardList.get(i).getLatitude();
            String longitude2 = onboardList.get(i).getLongitude();
            String POINT_NM = onboardList.get(i).getPoint_nm();
            Log.d("asdf",onboardList.get(i).getLatitude()+"/////"+onboardList.get(i).getLongitude());
            LatLng point = new LatLng(Double.parseDouble(latitude2), Double.parseDouble(longitude2));

            MarkerOptions mOptions2 = new MarkerOptions();
            mOptions2.title(POINT_NM);
            //mOptions2.snippet(address);
            mOptions2.position(point);

            mOptions2.snippet("자세히 보기");

            // 마커 추가
            map.addMarker(mOptions2);
            // 해당 좌표로 화면 줌
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(point[i], 15));
        }

        /*// 좌표(위도, 경도) 생성
        LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        // 마커 생성
        MarkerOptions mOptions2 = new MarkerOptions();
        mOptions2.title("search result");
        //mOptions2.snippet(address);
        mOptions2.position(point);
        // 마커 추가
        map.addMarker(mOptions2);
        // 해당 좌표로 화면 줌
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));*/
    }

    public void rock(){
        map.clear();
        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        rockList = mDbHelper.getRockData();

        // db 닫기
        mDbHelper.close();


        //String latitude = rockList.get(1).getLatitude(); // 위도
        //String longitude = rockList.get(1).getLongitude(); // 경도

        setPosition();
        for (int i=1; i<rockList.size(); i++){
            String latitude2 = rockList.get(i).getLatitude();
            String longitude2 = rockList.get(i).getLongitude();
            String POINT_NM = rockList.get(i).getPoint_nm();
            Log.d("asdf",rockList.get(i).getLatitude()+"/////"+rockList.get(i).getLongitude());
            LatLng point = new LatLng(Double.parseDouble(latitude2), Double.parseDouble(longitude2));

            MarkerOptions mOptions2 = new MarkerOptions();
            mOptions2.title(POINT_NM);
            //mOptions2.snippet(address);
            mOptions2.position(point);
            mOptions2.snippet("자세히 보기");
            // 마커 추가
            map.addMarker(mOptions2);
            // 해당 좌표로 화면 줌
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(point[i], 15));
        }

        /*// 좌표(위도, 경도) 생성
        LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        // 마커 생성
        MarkerOptions mOptions2 = new MarkerOptions();
        mOptions2.title("search result");
        //mOptions2.snippet(address);
        mOptions2.position(point);
        // 마커 추가
        map.addMarker(mOptions2);
        // 해당 좌표로 화면 줌
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));*/
    }

    public void fresh(){
        AsyncTask asyncTask = new AsyncTask();
        asyncTask.execute();
        setPosition();
    }

    public void sea(){
        AsyncTask2 asyncTask2 = new AsyncTask2();
        asyncTask2.execute();
        setPosition();
    }

    class AsyncTask extends android.os.AsyncTask<String,MarkerOptions,String>{
        @Override
        protected void onPreExecute() {
            map.clear();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //완료 후 메시지 출력
            Toast.makeText(org.tensorflow.demo.map.map.this ,s, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(MarkerOptions... values) {
            super.onProgressUpdate(values);
            // 마커 추가
            map.addMarker(values[0]);
            // 해당 좌표로 화면 줌
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(String... strings) {
            DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
            mDbHelper.createDatabase();
            mDbHelper.open();

            // db에 있는 값들을 model을 적용해서 넣는다.
            freshwaterList = mDbHelper.FreshWaterData();

            // db 닫기
            mDbHelper.close();

            List<Address> addressList = null;

            for (int i=1; i<freshwaterList.size()-1; i++){
                String name = freshwaterList.get(i).getName();
                String add = freshwaterList.get(i).getAddress();
                String fish = freshwaterList.get(i).getFish();

                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            add, // 주소
                            10); // 최대 검색 결과 개수
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                if (addressList != null) {
                    Log.d("result", addressList.get(0).toString());
                    System.out.println(addressList.get(0).toString());
                    // 콤마를 기준으로 split
                    String[] splitStr = addressList.get(0).toString().split(",");
                    String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
                    System.out.println(address);

                    String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                    String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                    System.out.println(latitude);
                    System.out.println(longitude);

                    // 좌표(위도, 경도) 생성
                    LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    // 마커 생성
                    MarkerOptions mOptions2 = new MarkerOptions();
                    mOptions2.title(name);
                    mOptions2.snippet(address+"\n자세히 보기");
                    mOptions2.position(point);

                    publishProgress(mOptions2);

                }else{
                    Log.d("result","검색 결과 없음");
                }
            }
            return "Freshwater 작업이 완료되었습니다.";
        }

    }

    class AsyncTask2 extends android.os.AsyncTask<String,MarkerOptions,String>{
        @Override
        protected void onPreExecute() {
            map.clear();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //완료 후 메시지 출력
            Toast.makeText(org.tensorflow.demo.map.map.this ,s, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(MarkerOptions... values) {
            super.onProgressUpdate(values);
            // 마커 추가
            map.addMarker(values[0]);
            // 해당 좌표로 화면 줌
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(String... strings) {
            DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
            mDbHelper.createDatabase();
            mDbHelper.open();

            // db에 있는 값들을 model을 적용해서 넣는다.
            seaList = mDbHelper.SeaData();

            // db 닫기
            mDbHelper.close();

            List<Address> addressList = null;

            for (int i=1; i<seaList.size()-1; i++){
                String name = seaList.get(i).getName();
                String add = seaList.get(i).getAddress();
                String fish = seaList.get(i).getFish();

                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            add, // 주소
                            10); // 최대 검색 결과 개수
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                if (addressList != null) {
                    Log.d("result", addressList.get(0).toString());
                    System.out.println(addressList.get(0).toString());
                    // 콤마를 기준으로 split
                    String[] splitStr = addressList.get(0).toString().split(",");
                    String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); // 주소
                    System.out.println(address);

                    String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                    String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                    System.out.println(latitude);
                    System.out.println(longitude);

                    // 좌표(위도, 경도) 생성
                    LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    // 마커 생성
                    MarkerOptions mOptions2 = new MarkerOptions();
                    mOptions2.title(name);
                    mOptions2.snippet(address+"\n자세히 보기");
                    mOptions2.position(point);

                    publishProgress(mOptions2);

                }else{
                    Log.d("result","검색 결과 없음");
                }
            }
            return "Sea 작업이 완료되었습니다.";
        }

    }

}
