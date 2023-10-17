insert into customer(name, surname, username, password, tax_code, role, is_available) values ('Paolo', 'Pacello', 'drPacello', 'dr_pacello99', '00122',1,TRUE);
insert into scan(is_available, mc_liter) values (true, 100);
insert into administrator(is_available, role, name, surname, username, password) values (true, 0, 'Luca', 'Rossi', 'lRossiAdmin', 'aaa90_!');
insert into condominium(is_available, address, administrator_id) values (true, 'via Kant 11', 1);
insert into apartment(floor_number, is_available, unit_number, condominium_id, customer_id, meter_id) values (3, true, 3, 1, 1, 1);
insert into bill(cost, payment_day, Delivering_Day, is_available, meter_id) values (200, '2023-12-15', '2023-10-15', true, 1);
insert into technician(is_available, role, name, surname, username, password) values (true, 3, 'Edwar', 'Azzaro', 'hacker', '123456ee!');