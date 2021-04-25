import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'employeemanagerapp';

  public employees: Employee[];
  public editEmployee: Employee;
  public deleteEmployee: Employee;
  
  constructor(private service: EmployeeService) { }

  ngOnInit() {
    this.getAll();
  }

  getAll(): void {
    this.service.getAllEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      
    );
  }

  public onOpenModel(employee: Employee, mode: string): void {    
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if(mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    
    if(mode === 'edit') {
      // button.setAttribute('data-target', '#addEmployeeModal');
      this.editEmployee = employee;
      button.setAttribute('data-target', '#editEmployeeModal');
    }

    if(mode === 'delete') {
      this.deleteEmployee = employee;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container.appendChild(button);
    button.click();
  }

  public onAddEmployee(addForm: NgForm):void {
    document.getElementById('add-employee-form').click();
    this.service.addEmployee(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getAll();
        addForm.reset();
      },
      (error: HttpErrorResponse)=>{
        alert(error.message);
        addForm.reset();
      }
    );    
  }

  public onUpdateEmployee(employee: Employee): void {
    document.getElementById('update-employee-form').click();
    this.service.updateEmployee(employee.id, employee).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getAll();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteEmployee(id: number): void {
    document.getElementById('delete-employee-form').click();
    this.service.deleteEmployee(id).subscribe(
      (response: void) => {
        console.log(response);
        this.getAll();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public searchEmployees(key: string): void {
    console.log(key);
    
    const result: Employee[] = [];
    for(const employee of this.employees) {

      if(employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1 
      || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        result.push(employee);
      }
    }
    console.log(result);
    
    this.employees = result;

    if(result.length === 0 || !key) {
      this.getAll();
    }
  }
}
