insert into temperature_unit(id, createdts, updatedts, value)
VALUES
(1, now(), now(), 'KELVIN'),
(2, now(), now(), 'CELSIUS'),
(3, now(), now(), 'FAHRENHEIT'),
(4, now(), now(), 'RANKINE');


insert into volume_unit(id, createdts, updatedts, value)
VALUES
(1, now(), now(), 'LITERS'),
(2, now(), now(), 'TABLESPOONS'),
(3, now(), now(), 'CUBICINCHES'),
(4, now(), now(), 'CUPS'),
(5, now(), now(), 'CUBICFEET'),
(6, now(), now(), 'GALLONS');


insert into temperature_conversion(id, createdts, updatedts, unitin_value, unitout_value, formula)
VALUES
(1, now(), now(), 'KELVIN', 'CELSIUS', '%f - 273.1'),
(2, now(), now(), 'KELVIN', 'FAHRENHEIT', '(%f - 273.15) * (9/5) + 32'),
(3, now(), now(), 'KELVIN', 'RANKINE', '%f * (9/5)'),
(4, now(), now(), 'CELSIUS', 'KELVIN', '%f + 273.1'),
(5, now(), now(), 'CELSIUS', 'FAHRENHEIT', '%f * (9/5) + 32'),
(6, now(), now(), 'CELSIUS', 'RANKINE', '%f * (9/5) + 491.67'),
(7, now(), now(), 'FAHRENHEIT', 'KELVIN', '(%f - 32) * (5/9) + 273.15'),
(8, now(), now(), 'FAHRENHEIT', 'CELSIUS', '(%f - 32) * (5/9)'),
(9, now(), now(), 'FAHRENHEIT', 'RANKINE', '%f + 459.67'),
(10, now(), now(), 'RANKINE', 'KELVIN', '%f * (5/9)'),
(11, now(), now(), 'RANKINE', 'FAHRENHEIT', '%f - 459.67'),
(12, now(), now(), 'RANKINE', 'CELSIUS', '(%f - 491.67) * (5/9)');


insert into volume_conversion(id, createdts, updatedts, unitin_value, unitout_value, formula)
VALUES
(1, now(), now(), 'LITERS', 'TABLESPOONS', '%f * 67.628'),
(2, now(), now(), 'LITERS', 'CUBICINCHES', '%f * 61.024'),
(3, now(), now(), 'LITERS', 'CUPS', '%f * 4.167'),
(4, now(), now(), 'LITERS', 'CUBICFEET', '%f / 28.317'),
(5, now(), now(), 'LITERS', 'GALLONS', '%f / 3.785'),
(6, now(), now(), 'TABLESPOONS', 'LITERS', '%f / 67.628'),
(7, now(), now(), 'TABLESPOONS', 'CUBICINCHES', '%f / 1.108'),
(8, now(), now(), 'TABLESPOONS', 'CUPS', '%f / 16.231'),
(9, now(), now(), 'TABLESPOONS', 'CUBICFEET', '%f / 1915'),
(10, now(), now(), 'TABLESPOONS', 'GALLONS', '%f / 256'),
(11, now(), now(), 'CUBICINCHES', 'LITERS', '%f / 61.024'),
(12, now(), now(), 'CUBICINCHES', 'TABLESPOONS', '%f * 1.108'),
(13, now(), now(), 'CUBICINCHES', 'CUPS', '%f / 14.646'),
(14, now(), now(), 'CUBICINCHES', 'CUBICFEET', '%f / 1728'),
(15, now(), now(), 'CUBICINCHES', 'GALLONS', '%f / 231'),
(16, now(), now(), 'CUPS', 'LITERS', '%f / 4.167'),
(17, now(), now(), 'CUPS', 'CUBICINCHES', '%f * 14.646'),
(18, now(), now(), 'CUPS', 'TABLESPOONS', '%f * 16.231'),
(19, now(), now(), 'CUPS', 'CUBICFEET', '%f / 118'),
(20, now(), now(), 'CUPS', 'GALLONS', '%f / 15.773'),
(21, now(), now(), 'CUBICFEET', 'LITERS', '%f * 28.317'),
(22, now(), now(), 'CUBICFEET', 'CUBICINCHES', '%f * 1728'),
(23, now(), now(), 'CUBICFEET', 'CUPS', '%f * 118'),
(24, now(), now(), 'CUBICFEET', 'TABLESPOONS', '%f * 1915'),
(25, now(), now(), 'CUBICFEET', 'GALLONS', '%f * 7.481'),
(26, now(), now(), 'GALLONS', 'LITERS', '%f * 3.785'),
(27, now(), now(), 'GALLONS', 'CUBICINCHES', '%f * 231'),
(28, now(), now(), 'GALLONS', 'CUPS', '%f * 15.773'),
(29, now(), now(), 'GALLONS', 'CUBICFEET', '%f / 7.481'),
(30, now(), now(), 'GALLONS', 'TABLESPOONS', '%f * 256');
