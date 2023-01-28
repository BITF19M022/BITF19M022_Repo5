package com.usman.employee_db;

import static android.widget.Toast.LENGTH_LONG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.usman.employee_db.data.DBHandler;
import com.usman.employee_db.model.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText empName, empPhoneNo, empSalary;
    Button save, editBtn, delete;
    ListView employeeView;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empName = findViewById(R.id.editTxtName);
        empPhoneNo = findViewById(R.id.editTxtPhone);
        empSalary = findViewById(R.id.editTxtSalary);
        employeeView = findViewById(R.id.listViewEmployee);

        save = findViewById(R.id.btnSave);
        editBtn = findViewById(R.id.btnEdit);
        delete = findViewById(R.id.btnDelete);

        List<Employee> empAllList = db.getAllEmployee();

        displayEmployee();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = empName.getText().toString();
                String phone = empPhoneNo.getText().toString();
                String salary = empSalary.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty() && !salary.isEmpty())
                {
                    Employee emp = new Employee(name, phone, Integer.parseInt(salary));
                    db.addEmployee(emp);
                    empName.getText().clear();
                    empPhoneNo.getText().clear();
                    empSalary.getText().clear();
                    Toast.makeText(MainActivity.this, "Employee Added", Toast.LENGTH_SHORT).show();
                    displayEmployee();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "All Field Required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final int[] updatedID = new int[1];
        employeeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Employee emp = empAllList.get(position);
                empName.setText(emp.getEmpName());
                empPhoneNo.setText(emp.getEmpPhoneNo());
                empSalary.setText(Integer.toString(emp.getEmpSalary()));
                updatedID[0] = emp.getEmpID();
            }
        });


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = empName.getText().toString();
                String phone = empPhoneNo.getText().toString();
                String salary = empSalary.getText().toString();
                Employee empObj = new Employee((int) updatedID[0], name, phone, Integer.parseInt(salary));
                db.updateEmployee(empObj);
                empName.getText().clear();
                empPhoneNo.getText().clear();
                empSalary.getText().clear();
                displayEmployee();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = empName.getText().toString();
                String phone = empPhoneNo.getText().toString();
                String salary = empSalary.getText().toString();
                Employee empObj = new Employee((int) updatedID[0], name, phone, Integer.parseInt(salary));
                db.deleteEmployee(empObj);
                empName.getText().clear();
                empPhoneNo.getText().clear();
                empSalary.getText().clear();
                displayEmployee();
            }
        });

    }

    public void displayEmployee()
    {
        List<Employee> empList = db.getAllEmployee();
        ArrayAdapter<Employee> empAdapter = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, empList)
        {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setText(empList.get(position).toString());
                return view;
            }
        };
        employeeView.setAdapter(empAdapter);
    }

}