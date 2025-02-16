/* Because the Name is unique, not duplicates will be added */
/* MARKER each command has to have on ONE line! */
insert into coffees (name, price) values ('Arabic', 56.30) on conflict do nothing;
insert into coffees (name, price) values ('India', 32.90) on conflict do nothing;
insert into coffees (name, price) values ('Mehar', 44.10) on conflict do nothing;
insert into coffees (name, price) values ('Cajole', 17.80) on conflict do nothing;