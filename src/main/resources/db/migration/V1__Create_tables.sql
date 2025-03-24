create table users(
  id_user int PRIMARY KEY,
  name varchar(30) not null,
  password text not null,
  bday date not null,
  phone varchar(30) not null,
  mail varchar(30) not null
);
create table categories(
  id_category int PRIMARY KEY,
  title varchar(20) not null
);
create table recipes(
  id_recipe int PRIMARY KEY,
  id_user int,
  title varchar(30) not null,
  id_category int,
  description text ,
  manual text not null,
  time varchar(30) not null,
  picture bytea,
  foreign key(id_user) references users(id_user),
  foreign key(id_category) references categories(id_category)
);

create table ingredients(
  id_ingredient int PRIMARY KEY,
  title_ingredient varchar(15) not null,
  unit_measure varchar(20) not null
);

create table composition_recipe(
  id_recipe int,
  id_ingredient int,
  quantity decimal(5,2),
  primary key (id_recipe, id_ingredient),
  foreign key(id_recipe) references recipes(id_recipe),
  foreign key(id_ingredient) references ingredients(id_ingredient)
);

create table shopping_list(
  id_user int,
  id_ingredient int,
  quantity decimal(5,2) not null,
  status bool,
  primary key (id_user, id_ingredient),
  foreign key(id_user) references users(id_user),
  foreign key(id_ingredient) references ingredients(id_ingredient)
);