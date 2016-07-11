package com.anuweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AnuWeatherOpenHelper extends SQLiteOpenHelper {
	
	public AnuWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/*
	 * /(non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 * province表建表语句
	 */
	public static  final String CREATE_PROVINCE = "create table Province("
			+"id integer primary key autoincrement,"
			+"province_name text,"
			+"province_code text)";
	
	/*
	 * /(non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 * city表建表语句
	 */
	public static final String CREATE_CITY = "create table City("
			+"id integer primary key autoincrement,"
			+"city_name text,"
			+"city_code text,"
			+"province_id integer)";
	
	/*
	 * /(non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 * country建表语句
	 */
	public static final String CREATE_COUNTRY = "create table Country("
			+"id integer primary key autoincrement,"
			+"country_name text,"
			+"country_code text,"
			+"cityz_id integer)";

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(CREATE_PROVINCE);
		arg0.execSQL(CREATE_CITY);
		arg0.execSQL(CREATE_COUNTRY);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
