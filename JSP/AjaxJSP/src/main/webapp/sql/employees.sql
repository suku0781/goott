select * from employees;

select e.*, d.department_name from employees e inner join departments d on e.department_id = d.department_id;

select e.*, d.department_name from employees e inner join departments d on e.department_id = d.department_id;

select * from employees where manager_id = ?;
SELECT * FROM jobs;

select e.*, d.department_name from Employees e inner join departments d on e.department_id = d.department_id order by e.employee_id;
select e.*, d.department_name from employees e inner join departments d on e.department_id = d.department_id where LOWER(first_name) like '%son%' or LOWER(last_name) like '%son%' order by e.salary;

select * from employees where employee_id = 301;


select e.*, d.department_name from employees e inner join departments d on e.department_id = d.department_id where e.employee_id = ?;
update employees set first_name = ? where employee_id = ?;

-- update문을 수행할 때 유저가 어떤 값을 수정할 지 모른다. 그래서 모든 경우의 쿼리문을 다 만들 수 없다. 
-- 1. 먼저 수정할 사원의 정보를 데이터입력 UI에 바인딩 시킨 후 모든 값을 가져온다. 
-- 2. 모든 컬럼의 값을 업데이터 시켜준다. 
-- 
update employees set first_name = ? where employee_id = ?;