package com.usman.employee_db.data;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.usman.employee_db.model.Employee;
import com.usman.employee_db.params.Paramters;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context)
    {
        super(context, Paramters.DB_NAME, null, Paramters.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + Paramters.TABLE_NAME + "("
                + Paramters.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Paramters.KEY_NAME + " TEXT,"
                + Paramters.KEY_PHONE + " TEXT,"
                + Paramters.KEY_SALARY + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addEmployee(Employee emp)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Paramters.KEY_NAME, emp.getEmpName());
        value.put(Paramters.KEY_PHONE, emp.getEmpPhoneNo());
        value.put(Paramters.KEY_SALARY, emp.getEmpSalary());

        db.insert(Paramters.TABLE_NAME, null, value);
        db.close();
    }

    public List<Employee> getAllEmployee()
    {
        List<Employee> empList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " +  Paramters.TABLE_NAME;
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do {
                Employee emp = new Employee();
                emp.setEmpID(Integer.parseInt(cursor.getString(0)));
                emp.setEmpName(cursor.getString(1));
                emp.setEmpPhoneNo(cursor.getString(2));
                emp.setEmpSalary(Integer.parseInt(cursor.getString(3)));
                empList.add(emp);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return empList;
    }

    public void updateEmployee(Employee emp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Paramters.KEY_NAME, emp.getEmpName());
        values.put(Paramters.KEY_PHONE, emp.getEmpPhoneNo());
        values.put(Paramters.KEY_SALARY, emp.getEmpSalary());

        db.update(Paramters.TABLE_NAME, values, Paramters.KEY_ID + " = ?",
                  new String[]{String.valueOf(emp.getEmpID())});
        db.close();
    }

    public void deleteEmployee(Employee emp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Paramters.TABLE_NAME, Paramters.KEY_ID + " = ?",
                new String[]{String.valueOf(emp.getEmpID())});
        db.close();
    }
}
