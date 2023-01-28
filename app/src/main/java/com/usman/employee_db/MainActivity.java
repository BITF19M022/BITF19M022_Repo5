package com.usman.employee_db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
                    Toast.makeText(MainActivity.this, "Employee Added", Toast.LENGTH_SHORT).show();
                    displayEmployee();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "All Field Required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = empName.getText().toString();
                String phone = empPhoneNo.getText().toString();
                String salary = empSalary.getText().toString();

                Employee emp = new Employee(name, phone, Integer.parseInt(salary));
                db.updateEmployee(emp);
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