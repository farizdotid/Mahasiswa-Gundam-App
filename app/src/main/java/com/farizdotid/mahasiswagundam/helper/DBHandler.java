package com.farizdotid.mahasiswagundam.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.farizdotid.mahasiswagundam.model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by farizdotid on 25-Oct-16.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_mahasiswagundam"; // NAMA DATABASE
    private static final String TABLE_MAHASISWA = "table_mahasiswagundam"; // NAMA TABEL
    private static final String COLUMN_ID = "id"; // NAMA KOLOM ID
    private static final String COLUMN_NAMA = "nama"; // NAMA KOLOM NAMA
    private static final String COLUMN_TEMPATLAHIR = "tempat_lahir"; // NAMA KOLOM TEMPAT LAHIR

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // FUNGSI UNTUK MEMBUAT DATABASENYA
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_MAHASISWA + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAMA + " TEXT,"
                + COLUMN_TEMPATLAHIR + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // FUNGSI UNTUK MENGECEK DATABASE ADA ATAU TIDAK.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        onCreate(db);
    }

    // FUNGSI UNTUK TAMBAH DATA MAHASISWA
    public void tambahMahasiswa(Mahasiswa mahasiswa){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_TEMPATLAHIR, mahasiswa.getTempat_lahir());

        db.insert(TABLE_MAHASISWA, null, values);
        db.close();
    }

    // FUNGSI UNTUK AMBIL 1 DATA MAHASISWA
    public Mahasiswa getMahasiswa(int id_mahasiswa){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MAHASISWA, new String[]{COLUMN_ID, COLUMN_NAMA, COLUMN_TEMPATLAHIR},
                COLUMN_ID + "=?", new String[]{String.valueOf(id_mahasiswa)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Mahasiswa mahasiswa = new Mahasiswa(cursor.getString(1), cursor.getString(2));
        return mahasiswa;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA MAHASISWA
    public List<Mahasiswa> getSemuaMahasiswa(){
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_MAHASISWA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Mahasiswa mahasiswa = new Mahasiswa(cursor.getString(1), cursor.getString(2));
                mahasiswaList.add(mahasiswa);
            } while (cursor.moveToNext());
        }
        return mahasiswaList;
    }

    // FUNGSI MENGHITUNG ADA BEBERAPA DATA
    public int getMahasiswaCount(){
        String countQuery = "SELECT * FROM " + TABLE_MAHASISWA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // FUNGSI UPDATE DATA MAHASISWA
    public int updateDataMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());
        values.put(COLUMN_TEMPATLAHIR, mahasiswa.getTempat_lahir());
        return db.update(TABLE_MAHASISWA, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(mahasiswa.getId())});
    }

    // FUNGSI HAPUS DATA 1 MAHASISWA
    public void hapusDataMahasiswa(Mahasiswa mahasiswa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA, COLUMN_ID + " = ?",
                new String[]{String.valueOf(mahasiswa.getId())});
        db.close();
    }

    // FUNGSI UNTUK MENGHAPUS SEMUA DATA MAHASISWA
    public void hapusSemuaDataMahasiswa(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_MAHASISWA);
    }
}
