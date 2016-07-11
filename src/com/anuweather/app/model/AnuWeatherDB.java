package com.anuweather.app.model;

import java.util.ArrayList;
import java.util.List;

import com.anuweather.app.db.AnuWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AnuWeatherDB {
	//���ݿ�����
	public static final String DB_NAME = "anu_weather";
	
	//���ݿ�汾
	private static final int VERSION = 1;
	
	private static AnuWeatherDB anuWeatherDB;
	
	private SQLiteDatabase db;
	
	//�����췽��˽�л�
	private AnuWeatherDB(Context context){
		AnuWeatherOpenHelper dbHelper = new AnuWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	//��ȡAnuWeatherDBʵ��
	public synchronized static AnuWeatherDB getInstance(Context context){
		if(anuWeatherDB == null){
			anuWeatherDB = new AnuWeatherDB(context);
		}
		return anuWeatherDB;
	}
	
	
	//��provinceʵ���洢�����ݿ���
	public void saveProvince(Province province){
		if(province != null){
			ContentValues values = new ContentValues();
			values.put("provice_name", province.getProvinceName());
			values.put("provice_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	
	
	//�����ݿ��ȡȫ����ʡ����Ϣ
	public List<Province> loadProvinces(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		if(cursor != null){
			cursor.close();
		}
		return list;
	}
	
	//��cityʵ�����浽���ݿ���
	public void saveCity(City city){
		if(city != null){
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		} 
	}
	
	//�����ݿ��ȡĳʡ�����еĳ�����Ϣ
	
	public List<City> loadCities(int provinceId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		if(cursor != null){
			cursor.close();
		}
		return list;
		
	}
	
	//��Countryʵ�����浽���ݿ���
	public void saveCountries(Country country){
		if(country != null){
			ContentValues values = new ContentValues();
			values.put("id", country.getId());
			values.put("country_name", country.getCountryName());
			values.put("country_code", country.getCountryCode());
			values.put("city_id", country.getCityId());
			db.insert("Country", null, values);
		}
	}
	
	//�����ݿ��ȡĳ���������е�����Ϣ
	
	public List<Country> loadCountries(int cityId){
		List<Country> list = new ArrayList<Country>();
		Cursor cursor = db.query("Country", null, "city_id = ?", new String[]{String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Country country = new Country();
				country.setId(cursor.getInt(cursor.getColumnIndex("id")));
				country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
				country.setCountryCode(cursor.getString(cursor.getColumnIndex("country-code")));
				country.setCityId(cityId);
			}while(cursor.moveToNext());
		}
		if(cursor != null){
			cursor.close();
		}
		return list;
	}
}
