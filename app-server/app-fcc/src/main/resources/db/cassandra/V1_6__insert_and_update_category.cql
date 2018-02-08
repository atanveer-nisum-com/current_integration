UPDATE app_list SET value = value + {'shoes','home'} where key = 'root';
UPDATE app_list SET value = value + {'activewear','coats'} where key = 'women';
insert into app_list(key, type, value) values('shoes','category',{'booties', 'comfort','boots'});
insert into app_list(key, type, value) values('home','category',{'kitchen', 'luggage & backpacks','boots'});